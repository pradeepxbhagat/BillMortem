package parser;

import report.TransactionReport;
import util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class HdfcBillParser implements Parser {
    @Override
    public TransactionReport parse(String raw) {
        if(isStringEmpty(raw)) {
            TransactionReport transactionReport = new TransactionReport();
            String[] lines = getLines(raw);
            int transactionStartIndex = getTransactionStartingIndex(lines);
            if(startedIndexFound(transactionStartIndex)) {
                int transactionEndIndex = getTransactionEndIndex(lines, transactionStartIndex);

                List<String> transactions = new ArrayList<>();
                for (int i = transactionStartIndex; i < transactionEndIndex; i++) {
                    String transaction = isTransaction(lines[i].trim().toUpperCase());
                    if (transaction != null) {
                        transactions.add(transaction);
                    }
                }
                transactionReport.setContents(transactions);
                return transactionReport;
            }
        }
        return null;
    }

    private boolean startedIndexFound(int transactionStartIndex) {
        return transactionStartIndex != -1;
    }

    private boolean isStringEmpty(String raw) {
        return raw.trim().length() > 0;
    }

    private String isTransaction(String raw) {
        String[] words = raw.split(" ");
        return Util.isDate(words[0]) ? raw : null;
    }

    private int getTransactionEndIndex(String[] lines, int transactionStartIndex) {
        while (!lines[transactionStartIndex++].trim().equals("Reward Points Summary")&& transactionStartIndex < lines.length);
        return --transactionStartIndex;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private int getTransactionStartingIndex(String[] lines) {
        int i =0;
        while(!lines[i++].trim().equals("Date  Transaction Description Amount (in Rs.)") && i < lines.length);

        return i == lines.length ? -1 :i+2;
    }

    private String[] getLines(String raw) {
        return raw.split(System.getProperty("line.separator"));
    }
}
