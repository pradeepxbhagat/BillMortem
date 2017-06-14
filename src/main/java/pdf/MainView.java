package pdf;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by pp00344204 on 13/06/17.
 */
public class MainView {
    private JPanel panel1;
    private JButton button1;
    private JFrame frame;

    public MainView(JFrame frame) {
        this.frame = frame;
        setFileChooserClickLister(button1);
    }

    public static void main(String[] args) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("MainView");
        frame.setLocation(dim.width/2-frame.getSize().width/2 - 200, dim.height/2-frame.getSize().height/2  -200);
        frame.setContentPane(new MainView(frame).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.pack();
        frame.setVisible(true);
        new MainView(frame);
    }

    private void setFileChooserClickLister(JButton btnFileChooser) {
        btnFileChooser.addActionListener(e -> {
            final JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(frame);
            if (i == JFileChooser.APPROVE_OPTION) {
                String filepath = getChooseFilePath(fc);
//                System.out.print(filepath);

                String pdfData = readDocument(filepath);

                if (pdfData != null) {
//                    System.out.print(pdfData);
                    openPdfReader(pdfData);
                }

            }
        });
    }

    private void openPdfReader(String pdfData) {
        close();
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("PdfReaderView");
//        frame.setLocation(dim.width/2-frame.getSize().width/2 - 200, dim.height/2-frame.getSize().height/2  -200);

        frame.setContentPane(new PdfReaderView(pdfData).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }

    private void close() {
        frame.dispose();
    }

    private String getChooseFilePath(JFileChooser fc) {
        File f = fc.getSelectedFile();
        return f.getPath();
    }

    private String readDocument(final String filepath) {
        PdfReader reader = PdfBoxReader.getReader();
        try {
            return reader.read(filepath);
        } catch (PdfReaderException e1) {
            switch (e1.getExceptionType()) {
                case PdfReaderException.ExceptionType.INVALID_PDF_FILE:
                    onInvalidFileSelected();
                    break;
                case PdfReaderException.ExceptionType.PASSWORD_PROTECTED:
                    String message ="This Pdf is passwrod protected. Please enter password";
                    openPasswordInputDialog(message, filepath);
                    break;
            }
        }
        return null;
    }

    private String readProtectedDocument(String filepath, String password) {
        PdfReader reader = PdfBoxReader.getReader();
        try {
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

    private void openPasswordInputDialog(String message, final String filepath) {
        PdfPasswordGraber.PasswordListener listener = new PdfPasswordGraber.PasswordListener() {
            @Override
            public void grabPassword(String password) {
//                System.out.print("Password is: " + password);
                String documentData = readProtectedDocument(filepath, password);
                if(documentData != null) {
//                    System.out.print(documentData);
                    openPdfReader(documentData);
                }
//                close();
            }

            @Override
            public void onDialogCancel() {
            }
        };

        PdfPasswordGraber dialog = new PdfPasswordGraber(listener,message);
        dialog.pack();
        dialog.setVisible(true);
       // System.exit(0);
    }

    private void onInvalidFileSelected() {
        JOptionPane.showMessageDialog(frame, "Please select a pdf file");
    }


}
