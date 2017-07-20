package com.billmortam.transaction;

import org.junit.Assert;
import org.junit.Test;
import com.billmortam.report.TransactionReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class TransactionSearchTest {

    @Test
    public void search__test(){
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        TransactionReport searchReport = search.searchTransaction("PAYTM");

        Assert.assertTrue(searchReport.getContents().size() > 0);
    }

    @Test
    public void multiple_search_option_search__test() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        TransactionReport searchReport = search.searchTransaction("PAYTM,PATANJALI");

        Assert.assertTrue(searchReport.getContents().size() > 0);
    }

    @Test
    public void test_invidual_result_for_multiple_searchs() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        Map<String,TransactionReport> searchReport = search.getIndividualSearchTransaction("PAYTM,PATANJALI");

        Assert.assertTrue(searchReport.get("PAYTM").getContents().size() ==3 &&searchReport.get("PATANJALI").getContents().size() ==1 );
    }

    @Test
    public void test_invidual_result_for_single_search() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        Map<String,TransactionReport> searchReport = search.getIndividualSearchTransaction("PAYTM");

        Assert.assertTrue(searchReport.get("PAYTM").getContents().size() ==3 );
    }

    @Test
    public void test_space_sperated_search_term() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        Map<String,TransactionReport> searchReport = search.getIndividualSearchTransaction("PAYTM APP");

        Assert.assertTrue(searchReport.get("PAYTM APP").getContents().size() ==1 );
    }

    @Test
    public void test_search_by_date() {
        List<Transaction> contents = new ArrayList<>();
        contents.add(new Transaction("15/05/2017", "PAYTM APP              NOIDA", 100.00f));
        contents.add(new Transaction("16/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 100.00f));
        contents.add(new Transaction("17/05/2017", "PAYTM MOBILE SOLUT INR www.paytm.in", 64.00f));
        contents.add(new Transaction("18/05/2017", "PATANJALI              PUNE ", 760.00f));
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);


        TransactionSearch search = TransactionSearch.getSearchEngine(transactionReport);
        Map<String, TransactionReport> searchReport = search.getIndividualSearchTransaction("15/05/2017");

        Assert.assertTrue(searchReport.get("15/05/2017").getContents().size() == 1);
    }
}
