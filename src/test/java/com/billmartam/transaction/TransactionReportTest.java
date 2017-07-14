package com.billmartam.transaction;

import org.junit.Assert;
import org.junit.Test;
import com.billmartam.report.TransactionReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 19/06/17.
 */
public class TransactionReportTest {

    @Test
    public void testtotalOfTransactionReport(){
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        double result = transactionReport.getTotalExpenditure();
        double actual = 1024;
        Assert.assertEquals(actual, result,0.0);
    }

    @Test
    public void testformattedtotalOfTransactionReport(){
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
        Assert.assertEquals(3,size);

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
        Assert.assertEquals(1000.50f,total,0);

    }
}