package com.billmartam.report;

import com.billmartam.pdf.Pdf;
import com.billmartam.transaction.Transaction;
import com.billmartam.transaction.TransactionSearch;
import com.billmartam.util.TextSearch;
import com.billmartam.util.Util;

import java.io.Serializable;
import java.util.*;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class TransactionReport implements Serializable{
    private List<Transaction> contents;
    private Pdf pdf;

    public TransactionReport() {
    }

    public TransactionReport(List<Transaction> contents) {
        this.contents = contents;
    }

    public List<Transaction> getContents() {
        return contents;
    }

    public void setContents(List<Transaction> contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionReport that = (TransactionReport) o;

        return contents != null ? contents.equals(that.contents) : that.contents == null;
    }

    @Override
    public int hashCode() {
        return contents != null ? contents.hashCode() : 0;
    }

    @Override
    public String toString() {
        String result = "";
        for(Transaction content : contents){
            result+=content.getDate()+" "+content.getDescription()+" "+content.getPrice()+"\n";
        }
        return result.trim();
    }

    public double getTotalExpenditure(){
        double total = 0.0;
        for (Transaction transaction : contents){
            if(!transaction.isCredit()) {
                total += transaction.getPrice();
            }
        }

        return total;
    }

    public Set getKeys() {
        Set keys = new HashSet();
        for(Transaction key : contents){
            keys.add(key.getDescription().toUpperCase());
        }
        return keys;
    }

    public String getFormattedTotalExpenditure() {
        return Util.getTwoDecimalFormat(getTotalExpenditure());
    }

    public void setOrigin(Pdf pdf) {
        this.pdf = pdf;
    }

    public Pdf getOrigin() {
        return pdf;
    }

    public Map<String, List<Transaction>> getDistinctReport() {
        Set keys = getKeys();
        TransactionSearch search = TransactionSearch.getSearchEngine(this);
        Map<String, TransactionReport> transactions = search.getIndividualSearchTransaction(Util.join(keys));

        Map<String, List<Transaction>> distinctReport = new HashMap<>();
        for(String key : transactions.keySet()){
            distinctReport.put(key, transactions.get(key).getContents());
        }
        return distinctReport;
    }


    public Map<String, Double> getDistinctKeyTotalReport() {
        Set keys = getKeys();
        TransactionSearch search = TransactionSearch.getSearchEngine(this);
        Map<String, TransactionReport> transactions = search.getIndividualSearchTransaction(Util.join(keys));

        Map<String, Double> distinctReport = new HashMap<>();
        for(String key : transactions.keySet()){
            distinctReport.put(key, transactions.get(key).getTotalExpenditure());
        }
        return distinctReport;
    }

    public Map<String, Double> getArtificialDistinctKeyTotalReport() {
        Set keys = getKeys();
        TransactionSearch search = TransactionSearch.getSearchEngine(this);
        Map<String, TransactionReport> transactions = search.getIndividualSearchTransaction(Util.join(keys));

        List<String> keyList = getKeySetAsList(keys);
        Map<String, Double> result = new HashMap<>();
        for(int i=0; i<keyList.size(); i++){
            List<String> words = getKeyWords(keyList.get(i));
            for(int j=0; j<words.size();j++){
                for (int k=i+1; k<keyList.size();k++){
                    if(TextSearch.match(words.get(j),keyList.get(k))){
                        double total;
                        if(result.containsKey(words.get(j))){
                               total = result.get(words.get(j));
                        }else{
                            total = transactions.get(keyList.get(i)).getTotalExpenditure();
                        }

                        total+=transactions.get(keyList.get(k)).getTotalExpenditure();

                        result.put(words.get(j),total);
                    }
                }
            }
        }

        return result;
    }

    private List<String> getKeyWords(String key) {
        List<String> words = new ArrayList<>();
        String result = "";
        for(int i=0; i <key.length(); i++){
            if(key.charAt(i) == ' ' && result.trim().length() > 0){
                if(isValidKey(result)) {
                    words.add(result);
                }
                result="";
            }else if(key.charAt(i) != 32){
                result+=key.charAt(i);
            }
        }
        if(words.size() == 0 && isValidKey(result)){
            words.add(result);
        }
        return words;
    }

    private boolean isValidKey(String result) {
        return result.length() > 1 && !Util.inIgnoreKeyList(result);
    }

    private List<String> getKeySetAsList(Set keys) {
        return new ArrayList<>(keys);
    }

}
