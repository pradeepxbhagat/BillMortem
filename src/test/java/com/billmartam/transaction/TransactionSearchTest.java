package com.billmartam.transaction;

import org.junit.Assert;
import org.junit.Test;
import com.billmartam.report.TransactionReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class TransactionSearchTest {

    @Test
    public void search__test(){
        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        TransactionReport searchReport = search.searchTransaction("PAYTM");

        Assert.assertTrue(searchReport.getContents().size() > 0);
    }

    @Test
    public void multiple_search_option_search__test() {
        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        TransactionReport searchReport = search.searchTransaction("PAYTM PATANJALI");

        Assert.assertTrue(searchReport.getContents().size() > 0);
    }

    @Test
    public void test_invidual_result_for_multiple_searchs() {
        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        Map<String,TransactionReport> searchReport = search.getIndividualSearchTransaction("PAYTM PATANJALI");

        Assert.assertTrue(searchReport.get("PAYTM").getContents().size() ==3 &&searchReport.get("PATANJALI").getContents().size() ==1 );
    }
}
