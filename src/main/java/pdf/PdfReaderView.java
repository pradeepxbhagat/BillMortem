package pdf;

import expenditure.ExpenditureCalculator;
import parser.HdfcBillParser;
import parser.Parser;
import report.TransactionReport;
import transaction.TransactionSearch;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

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
    private String pdfData;
    private Parser parser;
    private TransactionReport report;

    public PdfReaderView(String pdfData) {
        this.pdfData = pdfData;
        pdfBody.setContentType("text/html");
        report = getReport(pdfData);
        htmlPdfData = convertToHtml(report.toString());
        setPdfBody(htmlPdfData);

        setSearchButtonListener();
        setReloadButtonListener();
        setKeyDownForSearchtext();
        setPdfBodyMouseListener();
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
        txtSearch.setText(selectedText);
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
        setPdfBody(htmlPdfData);
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

            TransactionSearch search = TransactionSearch.getSearchEngine(report);
            searchText = modifySearch(searchText);
            TransactionReport searchedTransaction = search.searchTransaction(searchText);

            String result = getProcessedResult(searchedTransaction);
            pdfBody.setText(result);
        }
    }

    private String getProcessedResult(TransactionReport searchedTransaction) {
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,800);
        frame.pack();
        frame.setVisible(true);
    }
}
