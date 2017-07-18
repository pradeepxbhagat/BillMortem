package com.billmartam.report;

import com.billmartam.TImeComplexityTestCase;
import com.billmartam.parser.*;
import com.billmartam.pdf.Pdf;
import com.billmartam.transaction.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pp00344204 on 18/07/17.
 */
public class IntelligentReportTest extends TImeComplexityTestCase{
    @Test
    public void testIntelligentReportInstance() throws Exception {
        IntelligentReport report = new IntelligentReport();
        Assert.assertNotNull(report);
    }

    @Test
    public void testIntelligentReport() throws Exception {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT, INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 40.50f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        contents.add(new Transaction("18/05/2017", "SHREE SAI ENTERPRISES  PUNE ", 760.00f));
        contents.add(new Transaction("18/05/2017", "AMITA ENTERPRISES A P  PUNE", 760.00f));
        contents.add(new Transaction("18/05/2017", "MITUL ENTERPRISES PVT  MUMBAI ", 760.00f));
        contents.add(new Transaction("18/05/2017", "EMERGE              PUNE ", 760.00f));
        contents.add(new Transaction("18/05/2017", "EMERGE              PUNE ", 760.00f));
        contents.add(new Transaction("18/05/2017", "RACHIRA WINES ", 760.00f));
        contents.add(new Transaction("18/05/2017", "RUCHIRA WINES", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        IntelligentReport report = new IntelligentReport();
        Map<String, Double> processedReport = report.getReport(transactionReport);
//        Assert.assertEquals(2,processedReport.size());
    }

    @Test
    public void testIntelligentReportForHdfc() throws Exception {
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(HdfcBillParserTest.raw);
        TransactionReport report = parser.parse(pdf, false);

        IntelligentReport intelligentReport = new IntelligentReport();
        Map<String, Double> report1 = intelligentReport.getReport(report);
        Assert.assertTrue(report1.size() == 6);
    }

    @Test
    public void testIntelligentReportFoeCiti() throws Exception {
        Parser parser = new CitiBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(CitiBillParserTest.raw);
        TransactionReport report = parser.parse(pdf, false);

        IntelligentReport intelligentReport = new IntelligentReport();
        Map<String, Double> report1 = intelligentReport.getReport(report);
        Assert.assertTrue(report1.size() == 8);
    }
    @Test
    public void testIntelligentReportFoeIcici() throws Exception {
        Parser parser = new IciciBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(IciciBillParserTest.raw);
        TransactionReport report = parser.parse(pdf, false);

        IntelligentReport intelligentReport = new IntelligentReport();
        Map<String, Double> report1 = intelligentReport.getReport(report);
        Assert.assertTrue(report1.size() == 1);
    }

}
