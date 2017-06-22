package com.billmartam.pdf;

import com.billmartam.expenditure.ExpenditureCalculator;
import com.billmartam.parser.HdfcBillParser;
import com.billmartam.parser.Parser;
import com.billmartam.pdf.util.PdfFileOpener;
import com.billmartam.report.TransactionReport;
import com.billmartam.transaction.TransactionSearch;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.Map;

/**
 * Created by pp00344204 on 13/06/17.
 */
public class PdfReaderView {
    private String htmlPdfData;
    private JButton btnNew;
    protected JPanel panel1;
    private JTextField txtSearch;
    private JButton searchButton;
    private JEditorPane pdfBody;
    private JButton reloadButton;
    private JEditorPane searchBody;
    private JFrame frame;
    private String pdfData;
    private Parser parser;
    private TransactionReport report;

    public PdfReaderView(JFrame frame, String pdfData) {
        this.frame = frame;
        initBody(pdfData);

        setSearchButtonListener();
        setReloadButtonListener();
        setKeyDownForSearchtext();
        setPdfBodyMouseListener();
        setBtnNewListener();
    }

    private void initBody(String pdfData) {
        this.pdfData = pdfData;
        searchBody.setContentType("text/html");
//        pdfBody.setContentType("text/html");

        report = getReport(pdfData);
//        htmlPdfData = convertToHtml(com.billmartam.report);
        setPdfBody(report);
    }

    private void setBtnNewListener() {
        btnNew.addActionListener( a->{
            selectNewPdf(frame);
        });
    }

    private void selectNewPdf(JFrame frame) {
        PdfFileOpener opener = PdfFileOpener.getOpener(frame,((data, filePath) -> {
            initBody(data);
            doReload();
        }));
        opener.shouldCache(true);
        opener.open();
    }

    private void setPdfBody(TransactionReport report) {
        pdfBody.setText(report == null ? "":report.toString());
    }

    private String convertToHtml(TransactionReport report) {
        return report == null ? "":convertToHtml(report.toString());
    }

    private void setPdfBody(String htmlPdfData) {
        pdfBody.setText(this.htmlPdfData);
    }

    private String convertToHtml(String raw) {
        return raw.replace("\r\n", " <br/>").replace("\n", "<br/> ");
    }

    private void setPdfBodyMouseListener() {
        pdfBody.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (pdfBody.getSelectedText() != null) { // See if they selected something
                    setSearchText(pdfBody.getSelectedText());
                    doSearch();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    private void setSearchText(String selectedText) {
        txtSearch.setText(txtSearch.getText().trim().length() > 0? txtSearch.getText().trim()+","+selectedText.toUpperCase() : selectedText.toUpperCase());
    }

    private void setKeyDownForSearchtext() {
        txtSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(getSearchText().trim().length() > 0) {
                    doSearch();
                }else{
                    doReload();
                }
            }
        });
    }

    private void setReloadButtonListener() {
        reloadButton.addActionListener(e -> {
            doReload();
        });
    }

    private void doReload() {
        txtSearch.setText("");
        searchBody.setText("");
    }

    private void setSearchButtonListener() {
        searchButton.addActionListener(e -> {
            doSearch();
        });
    }

    private void doSearch() {
        String searchText = getSearchText();
        if(searchText.trim().length() > 0) {
            TransactionReport report = getReport(pdfData);

            searchText = modifySearch(searchText);

            TransactionSearch search = TransactionSearch.getSearchEngine(report);
//            TransactionReport searchedTransaction = search.searchTransaction(searchText);
//            String result = getProcessedResult(searchedTransaction);

            Map<String, TransactionReport> searchedTransaction = search.getIndividualSearchTransaction(searchText);
            String result = getProcessedResult(searchedTransaction);

            searchBody.setText(result);
        }
    }

    private String getProcessedResult(Map<String, TransactionReport> searchedTransaction) {
        if(searchedTransaction == null) {
            return "";
        }
        double totalExpenditure = getTotal(searchedTransaction);
        StringBuffer buffer = new StringBuffer("<h3 style:'color:green'>Total expenditure: ");
        buffer.append(totalExpenditure);

        for(Map.Entry<String, TransactionReport> val : searchedTransaction.entrySet()) {
            buffer.append("<br/>");
            buffer.append("<h3>");
            buffer.append(val.getKey());
            buffer.append("</h3>");
            buffer.append("<h2> Total: ");
            buffer.append(val.getValue().getTotal());
            buffer.append("</h2>");
            buffer.append(val.getValue().toString().replace("\r\n", " <br/>").replace("\n", "<br/> "));
            buffer.append("<br/>");
        }
        return buffer.toString();
    }

    private double getTotal(Map<String, TransactionReport> searchedTransaction) {
        double total = 0;
        for(Map.Entry<String, TransactionReport> val : searchedTransaction.entrySet()){
            total+=val.getValue().getTotal();
        }
        return total;
    }

    private String getProcessedResult(TransactionReport searchedTransaction) {
        if(searchedTransaction == null) {
            return "";
        }
        double expenditure = Math.floor(ExpenditureCalculator.getCalculator().calculateTotalExpenditure(searchedTransaction));
        String result ="<h3 style:'color:green'>Total expenditure: ";
        String data = searchedTransaction.toString().replace("\r\n", " <br/>").replace("\n", "<br/> ");
        result+=expenditure+"</h3>\n"+"\n"+data;
        return result;
    }

    private String modifySearch(String searchText) {
        searchText = searchText.replace(" ",",");
        return searchText;
    }

    private TransactionReport getReport(String pdfData) {
        if(parser == null) {
            parser = new HdfcBillParser();
        }
        if(report == null) {
            report = parser.parse(pdfData);
        }
        return report;
    }

    private String getSearchText() {
        return txtSearch.getText();
    }

    public PdfReaderView() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PdfReaderView");
        frame.setContentPane(new PdfReaderView().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,800);
        frame.pack();
        frame.setVisible(true);
    }
}
