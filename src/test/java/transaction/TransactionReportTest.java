package transaction;

import org.junit.Assert;
import org.junit.Test;
import report.TransactionReport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pp00344204 on 19/06/17.
 */
public class TransactionReportTest {

    @Test
    public void testtotalOfTransactionReport(){
        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        double result = transactionReport.getTotal();
        double actual = 1024;
        Assert.assertEquals(actual, result,0.0);
    }
}