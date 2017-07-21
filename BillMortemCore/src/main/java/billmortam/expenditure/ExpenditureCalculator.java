package billmortam.expenditure;


import billmortam.report.TransactionReport;
import billmortam.transaction.Transaction;
import billmortam.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class ExpenditureCalculator {
    private ExpenditureCalculator() {
    }

    public float findExpenditure(String raw) {
        String[] words = getWords(raw);
        String lastWord = getLastWord(words);
        return Util.isFloatingNumber(lastWord) ? Float.parseFloat(lastWord) : -1;
    }

    private String getLastWord(String[] words) {
        return words[words.length -1].replace(",","");
    }

    private String[] getWords(String raw) {
        return raw.split(" ");
    }

    public List<Transaction> findExpenditures(String raw) {
        String[] lines = raw.split(System.getProperty("line.separator"));
        if (lines.length <=0){
            return null;
        }

        List<Transaction> transactions = new ArrayList<>();
        for (String line : lines) {
            float price = findExpenditure(line.trim());
            if (price != -1) {
                transactions.add(new Transaction(price));
            }
        }
        return transactions;
    }

    public double calculateTotalExpenditure(String raw) {
        List<Transaction> transactions = findExpenditures(raw);
        Double total = calculateTotalExpenditure(transactions);
        if (total != null) return total;

        return 0;
    }

    private Double calculateTotalExpenditure(List<Transaction> transactions) {
        if(transactions != null){
            double total =0;
            for(Transaction transaction : transactions){
                total+=transaction.getPrice();
            }

            return total;
        }
        return null;
    }

    public static ExpenditureCalculator getCalculator() {
        return new ExpenditureCalculator();
    }

    public double calculateTotalExpenditure(TransactionReport searchedTransactions) {
        List<Transaction> contents = searchedTransactions.getContents();
        double total = 0;
        for(Transaction content :contents){
            float price = content.getPrice();
            if(price != -1){
                total+=price;
            }
        }
        return total;
    }
}
