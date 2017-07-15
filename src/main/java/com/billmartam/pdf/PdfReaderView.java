package com.billmartam.pdf;

import com.billmartam.cache.CacheManager;
import com.billmartam.cache.ReportsCacheManager;
import com.billmartam.expenditure.ExpenditureCalculator;
import com.billmartam.parser.*;
import com.billmartam.pdf.util.PdfFileOpener;
import com.billmartam.report.TransactionReport;
import com.billmartam.transaction.TransactionSearch;
import com.billmartam.util.Util;
import com.sun.deploy.util.StringUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by pp00344204 on 13/06/17.
 */
public class PdfReaderView {
    public static final String CHART_ICON_PATH = "src/main/res/PieChart.jpeg";

    private static DefaultPieDataset dataset;
    private static JFreeChart chart;
    private String htmlPdfData;
    private JButton btnNew;
    protected JPanel panel1;
    private JTextField txtSearch;
    private JButton searchButton;
    private JEditorPane pdfBody;
    private JButton reloadButton;
    private JEditorPane searchBody;
    private JCheckBox useCacheCheckBox;
    private JButton clearCacheButton;
    private JScrollPane bodyPane;
    private JButton chartButton;
    private JButton showOriginButton;
    private JFrame frame;
    private boolean canUseCache;
    private Pdf pdfData;
    private Parser parser;
    private TransactionReport report;
    private CacheManager cacheManager;
    private boolean chartVisible;

    public PdfReaderView(JFrame frame, Pdf pdf) {
        this(frame, pdf, false);
    }

    public PdfReaderView(JFrame frame, Pdf pdf, boolean canUseCache) {
        this.frame = frame;
        this.canUseCache = canUseCache;
        useCacheCheckBox.setSelected(canUseCache);
        initBody(pdf);

        setSearchButtonListener();
        setReloadButtonListener();
        setKeyDownForSearchtext();
        setPdfBodyMouseListener();
        setBtnNewListener();
        setCacheCheckBoxListener();
        setClearCacheButtonListener();
        setChartButtonListener();
        setOriginButtonListener();
    }

    private void setOriginButtonListener() {
        showOriginButton.addActionListener(a->{
            doReload();
            hideChart();
           setSearchBody(convertToHtml(report.getOrigin().getData()));
        });
    }

    private void setChartButtonListener() {
        chartButton.addActionListener(a -> {
            if(!isChartVisible()) {
                setChart(report);
            }
        });
    }

    private void setClearCacheButtonListener() {
        clearCacheButton.addActionListener(a -> {
            if (cacheManager == null) {
                cacheManager = ReportsCacheManager.getManager();
            }
            String msg = cacheManager.clear() ? "Cache successfully cleared" : "Failed !!!";
            openPrompt(msg);
        });
    }

    private void openPrompt(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }

    private void setCacheCheckBoxListener() {
        useCacheCheckBox.addItemListener(e -> {
            canUseCache = false;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                canUseCache = true;
            }
        });
    }

    private void initBody(Pdf pdf) {
        this.parser = null;
        this.report = null;
        this.dataset = null;

        this.pdfData = pdf;
        searchBody.setContentType("text/html");
//        pdfBody.setContentType("text/html");

        report = getReport(pdf);
//        htmlPdfData = convertToHtml(com.billmartam.report);
        setPdfBody(report);
//        groupByDescription(report);

        setChart(report);
    }

    private void groupByDescription(TransactionReport report) {
        Set keys = report.getKeys();
        doSearch(StringUtils.join(keys, ","));
    }

    private void setBtnNewListener() {
        btnNew.addActionListener(a -> {
            selectNewPdf(frame);
        });
    }

    private void selectNewPdf(JFrame frame) {
        PdfFileOpener opener = PdfFileOpener.getOpener(frame);
        opener.setListener((data) -> {
            initBody(data);
            doReload();
        });
        opener.shouldCache(true);
        opener.open();
    }

    private void setPdfBody(TransactionReport report) {
        pdfBody.setText(report == null ? "" : "Total expenditure: " + report.getFormattedTotalExpenditure() + "\n \n" + report.toString());
    }

    private String convertToHtml(TransactionReport report) {
        return report == null ? "" : convertToHtml(report.toString());
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
                    doReload();
                    setSearchText(pdfBody.getSelectedText());
                    doSearch(getSearchText());
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
        txtSearch.setText(txtSearch.getText().trim().length() > 0 ? txtSearch.getText().trim() + "," + selectedText.toUpperCase() : selectedText.toUpperCase());
    }

    private void setKeyDownForSearchtext() {
        txtSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (getSearchText().trim().length() > 0) {
                    doSearch(getSearchText());
                } else {
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
        setSearchBody("");
        chartButton.setVisible(false);
        dataset = null;
    }

    private void setSearchButtonListener() {
        searchButton.addActionListener(e -> {
            doSearch(getSearchText());
        });
    }

    private void doSearch(String searchText) {
//        String searchText = getSearchText();
        if (searchText.trim().length() > 0) {
            TransactionReport report = getReport(pdfData);

            TransactionSearch search = TransactionSearch.getSearchEngine(report);
//            TransactionReport searchedTransaction = search.searchTransaction(searchText);
//            String result = getProcessedResult(searchedTransaction);

            Map<String, TransactionReport> searchedTransaction = search.getIndividualSearchTransaction(searchText);
            String result = getProcessedResult(searchedTransaction);

            hideChart();
            setSearchBody(result);
        }
    }

    private void setSearchBody(String result) {
        searchBody.setText(result);
    }

    private void hideChart() {
        if (isChartVisible()) {
            bodyPane.getViewport().add(searchBody);
            setChartVisible(false);
            saveChartIcon(chart);
        }
        updateChartButtonIcon();
    }

    private void updateChartButtonIcon() {
        try {
            chartButton.setVisible(true);
            String file = new File(CHART_ICON_PATH).getCanonicalPath();
            Image img = ImageIO.read(new File(file));
            img = img.getScaledInstance(75,50,2);
            chartButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void saveChartIcon(JFreeChart chart) {
        int width = 640;   /* Width of the image */
        int height = 480;  /* Height of the image */
        File pieChart = null;
        try {
            pieChart = new File( new File(CHART_ICON_PATH ).getCanonicalPath());
            ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProcessedResult(Map<String, TransactionReport> searchedTransaction) {
        if (searchedTransaction == null) {
            return "";
        }
        double totalExpenditure = getTotal(searchedTransaction);
        StringBuffer buffer = new StringBuffer("<h3 style:'color:green'>Total expenditure: ");
        buffer.append(Util.getTwoDecimalFormat(totalExpenditure));

        for (Map.Entry<String, TransactionReport> val : searchedTransaction.entrySet()) {
            buffer.append("<br/>");
            buffer.append("<h3>");
            buffer.append(val.getKey());
            buffer.append(" --Total: ");
            buffer.append(val.getValue().getFormattedTotalExpenditure());
            buffer.append("</h3>");
            buffer.append(val.getValue().toString().replace("\r\n", " <br/><br/>").replace("\n", "<br/> <br/>"));
            buffer.append("<br/>");
        }
        return buffer.toString();
    }

    private double getTotal(Map<String, TransactionReport> searchedTransaction) {
        double total = 0;
        for (Map.Entry<String, TransactionReport> val : searchedTransaction.entrySet()) {
            total += val.getValue().getTotalExpenditure();
        }
        return total;
    }

    private String getProcessedResult(TransactionReport searchedTransaction) {
        if (searchedTransaction == null) {
            return "";
        }
        double expenditure = Math.floor(ExpenditureCalculator.getCalculator().calculateTotalExpenditure(searchedTransaction));
        String result = "<h3 style:'color:green'>Total expenditure: ";
        String data = searchedTransaction.toString().replace("\r\n", " <br/>").replace("\n", "<br/> ");
        result += expenditure + "</h3>\n" + "\n" + data;
        return result;
    }

    private TransactionReport getReport(Pdf pdfData) {
        if (parser == null) {
            Identifier identifier = BillIdentifier.getIdentifier();
            ParserFactory factory = ParserFactory.getFactory();
            parser = factory.getParser(identifier.identify(pdfData));
        }
        if (report == null) {
            report = parser.parse(pdfData, canUseCache);
        }

        return report;
    }

    private String getSearchText() {
        return txtSearch.getText();
    }


    private void setChart(TransactionReport report) {
        txtSearch.setText("");
        chartButton.setVisible(false);
        setChartVisible(true);
        JPanel chartPanel = createChartPanel(report);
        bodyPane.getViewport().add(chartPanel);
    }

    public static JPanel createChartPanel(TransactionReport report) {
        JFreeChart chart = createChart(createDataset(report));
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    private static PieDataset createDataset(TransactionReport report) {
        if (dataset == null) {
            dataset = new DefaultPieDataset();
            Set keys = report.getKeys();
            for (Object key : keys) {
                double total = getGroupTotal((String) key, report);
                dataset.setValue((String) key, total);
            }
        }
        return dataset;
    }

    private static double getGroupTotal(String key, TransactionReport report) {
        TransactionSearch search = TransactionSearch.getSearchEngine(report);
        return search.searchTransaction(key).getTotalExpenditure();
    }

    private static JFreeChart createChart(PieDataset dataset) {
        chart = ChartFactory.createPieChart(
                "Expenditure",   // chart title
                dataset,          // data
                false,             // include legend
                true,
                false);

        return chart;
    }


    public void setChartVisible(boolean chartVisible) {
        this.chartVisible = chartVisible;
    }

    public boolean isChartVisible() {
        return chartVisible;
    }
}
