package com.billmartam.pdf.util;

import com.billmartam.pdf.*;
import com.billmartam.cache.FileSpecification;
import com.billmartam.cache.RecentFileCacheManager;
import com.billmartam.cache.CacheManager;

import javax.swing.*;
import java.io.File;

/**
 * Created by pp00344204 on 22/06/17.
 */
public class PdfFileOpener {
    private static PdfFileOpener opener;
    private PdfFileOpenerListener listener;
    private JFrame frame;
    private boolean cache;
    private PdfPasswordGraber dialog;

    private PdfFileOpener(JFrame frame) {
        this.frame = frame;
    }

    public static PdfFileOpener getOpener(JFrame frame){
        if(opener == null){
            opener = new PdfFileOpener(frame);
        }

        return opener;
    }

    public void open() {
        final JFileChooser fc = new JFileChooser();
        int i = fc.showOpenDialog(frame);
        if (i == JFileChooser.APPROVE_OPTION) {
            String filepath = getChooseFilePath(fc);
//                System.out.print(filepath);

            readFile(filepath);

        }
    }

    private String getChooseFilePath(JFileChooser fc) {
        File f = fc.getSelectedFile();
        return f.getPath();
    }

    public void readFile(String filepath) {
        Pdf pdfData = readDocument(filepath);

        if (pdfData != null) {
//                    System.out.print(pdfData);
//            openPdfReader(pdfData);
            sendSuccess(pdfData,filepath);
//            listener.onSuccess(pdfData, filepath);
        }
    }

    private void sendSuccess(Pdf pdfData, String filepath) {
        if(cache){
            persistFilePath(filepath);
        }
        pdfData.setFilePath(filepath);
        listener.onSuccess(pdfData);
    }

    private void persistFilePath(String filepath) {
        FileSpecification specification = new FileSpecification(filepath, System.currentTimeMillis());
        CacheManager storage = RecentFileCacheManager.getManager();
        storage.save(specification);
    }

    private Pdf readDocument(final String filepath) {
        PdfReader reader = PdfBoxReader.getReader();
        try {
            //            persistFilePath(filepath);
            return reader.read(filepath);
        } catch (PdfReaderException e1) {
            switch (e1.getExceptionType()) {
                case PdfReaderException.ExceptionType.INVALID_PDF_FILE:
                    onInvalidFileSelected();
                    break;
                case PdfReaderException.ExceptionType.PASSWORD_PROTECTED:
                    String message ="This Pdf is password protected. Please enter password";
                    openPasswordInputDialog(message, filepath);
                    break;
            }
        }
        return null;
    }

    private void openPasswordInputDialog(String message, final String filepath) {
        PdfPasswordGraber.PasswordListener passwordListener = new PdfPasswordGraber.PasswordListener() {
            @Override
            public void grabPassword(String password) {
                dialog.dispose();
                dialog = null;
                Pdf documentData = readProtectedDocument(filepath, password);
                if(documentData != null) {
//                    System.out.print(documentData);
//                    openPdfReader(documentData);
                    sendSuccess(documentData,filepath);
                }
//                close();
            }

            @Override
            public void onDialogCancel() {
                dialog = null;
            }
        };

        if(dialog == null) {
            dialog = new PdfPasswordGraber(passwordListener, message);
            dialog.pack();
            dialog.setVisible(true);
        }
        // System.exit(0);
    }

    private void onInvalidFileSelected() {
        JOptionPane.showMessageDialog(frame, "Please select a pdf file");
    }


    private Pdf readProtectedDocument(String filepath, String password) {
        PdfReader reader = PdfBoxReader.getReader();
        try {
            //            persistFilePath(filepath);
            return reader.read(filepath,password);
        } catch (PdfReaderException e1) {
            switch (e1.getExceptionType()) {
                case PdfReaderException.ExceptionType.INVALID_PASSWORD:
                    String message = "Password is invalid. Please enter correct password";
                    openPasswordInputDialog(message, filepath);
                    break;
            }
        }
        return null;
    }

    public void setListener(PdfFileOpenerListener listener) {
        this.listener = listener;
    }

    public void shouldCache(boolean cache) {
        this.cache = cache;
    }

    @FunctionalInterface
    public interface PdfFileOpenerListener{
        void onSuccess(Pdf data);
    }
}
