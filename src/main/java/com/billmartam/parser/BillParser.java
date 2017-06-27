package com.billmartam.parser;

import com.billmartam.report.TransactionReport;
import com.billmartam.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 06/06/17.
 */
abstract class BillParser implements Parser {
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

    protected String isTransaction(String raw) {
        String[] words = raw.split(" ");
        return Util.isHdfcDateFormat(words[0]) ? raw : null;
    }

    private int getTransactionEndIndex(String[] lines, int transactionStartIndex) {
        while (!lines[transactionStartIndex++].trim().equals(getTransactionEndIdentifier())&& transactionStartIndex < lines.length);
        return --transactionStartIndex;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    protected int getTransactionStartingIndex(String[] lines) {
        int i =0;
        while(i < lines.length && !lines[i].trim().equals(getTransactionStartIdentifier()) ){
            i++;
        }

        return i;
    }

    abstract protected String getTransactionStartIdentifier();

    abstract protected String getTransactionEndIdentifier();

    private String[] getLines(String raw) {
        return raw.split(System.getProperty("line.separator"));
    }
}
