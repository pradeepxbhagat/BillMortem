package com.billmartam.report;

import com.billmartam.transaction.Transaction;
import com.billmartam.util.Util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class TransactionReport implements Serializable{
    private List<Transaction> contents;

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
            keys.add(key.getDescription());
        }
        return keys;
    }

    public String getFormattedTotalExpenditure() {
        return Util.getTwoDecimalFormat(getTotalExpenditure());
    }
}
