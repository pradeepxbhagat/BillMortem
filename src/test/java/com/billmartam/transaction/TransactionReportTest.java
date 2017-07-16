package com.billmartam.transaction;

import com.billmartam.parser.HdfcBillParser;
import com.billmartam.parser.HdfcBillParserTest;
import com.billmartam.pdf.Pdf;
import org.junit.Assert;
import org.junit.Test;
import com.billmartam.report.TransactionReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pp00344204 on 19/06/17.
 */
public class TransactionReportTest {

    @Test
    public void testtotalOfTransactionReport() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        double result = transactionReport.getTotalExpenditure();
        double actual = 1024;
        Assert.assertEquals(actual, result, 0.0);
    }

    @Test
    public void testformattedtotalOfTransactionReport() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.55f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        String result = transactionReport.getFormattedTotalExpenditure();
        String actual = "1,024.55";
        Assert.assertEquals(actual, result);
    }

    @Test
    public void individualKeysTest() throws Exception {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        int size = transactionReport.getKeys().size();
        Assert.assertEquals(3, size);

    }

    @Test
    public void totaltest() throws Exception {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 40.50f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        double total = transactionReport.getTotalExpenditure();
        Assert.assertEquals(1000.50f, total, 0);

    }

    @Test
    public void origintest() throws Exception {
        Pdf pdf = new Pdf();
        pdf.setFilePath("some file path");
        pdf.setData("Date  Transaction Description Amount (in Rs.)\n" +
                "15/05/2017 PAYTM APP              NOIDA 100.00  \n" +
                "16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
                "17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00  \n" +
                "17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
                "18/05/2017 PATANJALI              PUNE 760.00  \n" +
                "Reward Points Summary\n");

        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 40.50f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));

        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        transactionReport.setOrigin(pdf);

        Assert.assertNotNull(transactionReport.getOrigin());

    }

    @Test
    public void getDistinctKeyValuesTest() throws Exception {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT, INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 40.50f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        Map<String, List<Transaction>> values = transactionReport.getDistinctReport();

        Assert.assertEquals(3,values.size());
    }

    @Test
    public void getDistinctKeyandtotalTest() throws Exception {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT, INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 40.50f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        Map<String, Double> values = transactionReport.getDistinctKeyTotalReport();
        String key = "PAYTM MOBILE SOLUT INR www.paytm.in";
        Assert.assertEquals(140.50,values.get(key.toUpperCase()),0);
    }

    @Test
    public void getArtificialDistinctKeyandtotalTest() throws Exception {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT, INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 40.50f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        Map<String, Double> values = transactionReport.getArtificialDistinctKeyTotalReport();
        Assert.assertEquals(240.5, values.get("PAYTM").doubleValue(),0);
    }
}
