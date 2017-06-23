package com.billmartam.pdf.util;

import com.billmartam.pdf.PdfBoxReader;
import com.billmartam.pdf.PdfPasswordGraber;
import com.billmartam.pdf.PdfReader;
import com.billmartam.pdf.PdfReaderException;
import com.billmartam.pdf.storage.FileSpecification;
import com.billmartam.pdf.storage.RecentFileStorage;
import com.billmartam.pdf.storage.Storage;

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

    private PdfFileOpener(JFrame frame, PdfFileOpenerListener listener) {
        this.frame = frame;
        this.listener = listener;
    }

    public static final  PdfFileOpener getOpener(JFrame frame,PdfFileOpenerListener listener){
        if(opener == null){
            opener = new PdfFileOpener(frame,listener);
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
        String pdfData = readDocument(filepath);

        if (pdfData != null) {
//                    System.out.print(pdfData);
//            openPdfReader(pdfData);
            sendSuccess(pdfData,filepath);
//            listener.onSuccess(pdfData, filepath);
        }
    }

    private void sendSuccess(String pdfData, String filepath) {
        if(cache){
            persistFilePath(filepath);
        }

        listener.onSuccess(pdfData,filepath);
    }

    private void persistFilePath(String filepath) {
        FileSpecification specification = new FileSpecification(filepath, System.currentTimeMillis());
        Storage storage = RecentFileStorage.getStorage();
        storage.save(specification);
    }

    private String readDocument(final String filepath) {
        PdfReader reader = PdfBoxReader.getReader();
        try {
            String data =  reader.read(filepath);
//            persistFilePath(filepath);
            return data;
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
                String documentData = readProtectedDocument(filepath, password);
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


    private String readProtectedDocument(String filepath, String password) {
        PdfReader reader = PdfBoxReader.getReader();
        try {
            String data =  reader.read(filepath,password);
//            persistFilePath(filepath);
            return data;
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
        void onSuccess(String data, String filePath);
    }
}