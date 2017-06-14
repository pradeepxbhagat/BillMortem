package transaction;

import report.TransactionReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class TransactionSearch {
    private TransactionReport transactionReport;

    @SuppressWarnings("unused")
    private TransactionSearch() {
    }

    private TransactionSearch(TransactionReport transactionReport) {
        this.transactionReport = transactionReport;
    }

    public TransactionReport searchTransaction(String searchTerm) {
        List<String> contents = transactionReport.getContents();
        if(contents.size() <= 0){
            return null;
        }
        searchTerm = searchTerm.toUpperCase();
        List<String> searchResults = new ArrayList<>();
        for(String transaction : contents){
            if(hasSearchTerm(transaction, searchTerm)){
                searchResults.add(transaction);
            }
        }
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(searchResults);
        return transactionReport;
    }

    private boolean hasSearchTerm(String transaction, String searchTerm) {
        searchTerm = searchTerm.replace(" ",",");
        String[] terms = searchTerm.split(",");
        boolean hasTerm = false;
        for(String term : terms){
            if(Arrays.asList(transaction.split(" ")).contains(term)){
                hasTerm = true;
            }
        }
        return hasTerm;
    }

    public static TransactionSearch getSearchEngine(TransactionReport from) {
        return new TransactionSearch(from);
    }
}
