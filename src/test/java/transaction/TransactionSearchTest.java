package transaction;

import org.junit.Assert;
import org.junit.Test;
import report.TransactionReport;

import java.util.ArrayList;
import java.util.List;

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
}
