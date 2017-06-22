package com.billmartam.pdf;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class PdfChooserView extends JFrame {

    public void init() {
        initRootContainer();
        initFileChooserButton();
    }

    private void initFileChooserButton() {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weighty = 1.0;
        constraints.weightx = 0.5;
        constraints.gridwidth=1;
        constraints.gridheight=1;

        JButton btnFileChooser = new JButton("Choose Pdf file");
        btnFileChooser.setSize(195, 30);
        add(btnFileChooser,constraints);
        pack();
        setFileChooserClickLister(btnFileChooser);
    }

    private void setFileChooserClickLister(JButton btnFileChooser) {
        btnFileChooser.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                String filepath = getChooseFilePath(fc);
                System.out.print(filepath);

                String pdfData = readDocument(filepath);

                if (pdfData != null) {
                    PdfDisplayView pdfDisplayView = new PdfDisplayView(this);
                    pdfDisplayView.displayPdf();
                }

            }
        });
    }

    private String readDocument(String filepath) {
        PdfReader reader = PdfBoxReader.getReader();
        try {
            return reader.read(filepath);
        } catch (PdfReaderException e1) {
            switch (e1.getExceptionType()) {
                case PdfReaderException.ExceptionType.INVALID_PDF_FILE:
                    onInvalidFileSelected();
            }
        }
        return null;
    }

    private void onInvalidFileSelected() {
        JOptionPane.showMessageDialog(this, "Please select a com.billmartam.pdf file");
    }

    private String getChooseFilePath(JFileChooser fc) {
        File f = fc.getSelectedFile();
        return f.getPath();
    }

    private void initRootContainer() {
        GridBagLayout grid = new GridBagLayout();
        setLayout(grid);

        setTitle("GridBag Layout Example");
        setSize(800, 700);
        setPreferredSize(getSize());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
