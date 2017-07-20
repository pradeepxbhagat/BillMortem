package com.billmortam.parser;

import com.billmortam.cache.CacheManager;
import com.billmortam.cache.ReportsCacheManager;
import com.billmortam.cache.TransactionReportCache;
import com.billmortam.pdf.Pdf;
import com.billmortam.report.TransactionReport;
import com.billmortam.transaction.Transaction;
import com.billmortam.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 06/06/17.
 */
abstract class BillParser implements Parser {

    @Override
    public TransactionReport parse(Pdf pdf, boolean canUseParserCache) {
        TransactionReport report;
        if (canUseParserCache) {
            TransactionReportCache cache = getCachedTransactionReport(pdf);
            if (cache == null) {
                report = getTransactionReport(pdf);
                saveToCache(report, pdf);
            } else {
                report = cache.getReport();
                System.out.println("from cache");
            }
            return report;
        } else {
            System.out.println("from file");
            return getTransactionReport(pdf);
        }
    }

    private boolean saveToCache(TransactionReport report, Pdf pdf) {
        TransactionReportCache cache = new TransactionReportCache(report, pdf.getFilePath());
        cache.setProtectionKey(pdf.getPassword());
        CacheManager manager = ReportsCacheManager.getManager();
        return manager.save(cache);
    }

    protected TransactionReport getTransactionReport(Pdf pdf) {
        if (isStringEmpty(pdf.getData())) {
            TransactionReport transactionReport = new TransactionReport();
            String[] lines = getLines(pdf.getData());
            int transactionStartIndex = getTransactionStartingIndex(lines);
            if (startedIndexFound(transactionStartIndex)) {
                List<Transaction> transactions = getTransactions(lines, transactionStartIndex, getTransactionEndIndex(lines, transactionStartIndex));
                transactionReport.setContents(transactions);
                transactionReport.setOrigin(pdf);
                return transactionReport;
            }
        }
        return null;
    }

    protected List<Transaction> getTransactions(String[] lines, int transactionStartIndex, int transactionEndIndex) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = transactionStartIndex; i < transactionEndIndex; i++) {
            String transactionStr = isTransaction(lines[i].trim().toUpperCase());
            if (transactionStr != null) {
                Transaction transaction = getTransactionModel(transactionStr);
                if(transaction.getPrice() != -1) {
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

    protected Transaction getTransactionModel(String transactionStr) {

        Transaction transaction = new Transaction();
        String[] words = transactionStr.split(" ");
        transaction.setDate(getDate(words));
        transaction.setCredit(isCredit(words));
        transaction.setPrice(getPrice(words));
        transaction.setDescription(getDescription(words));
        return transaction;
    }

    protected String getDate(String[] words) {
        return words[0];
    }

    protected String getDescription(String[] words) {
        int len = words.length - 2;
        if (isCredit(words)) {
            len--;
        }
        return getDescriptionString(words, len);
    }

    protected String getDescriptionString(String[] words, int len) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 1; i <= len; i++) {
//            String word = words[i].equals("")?" ":words[i];
            stringBuffer.append(words[i]);
            stringBuffer.append(" ");
        }
        return stringBuffer.toString().trim();
    }

    protected boolean isCredit(String[] words) {
        return words[words.length - 1].equals("CR");
    }

    protected float getPrice(String[] words) {
        float price = -1;
        String lastWord = words[words.length - 1].replace(",","");
        if(isCredit(words)){
            price = Float.parseFloat(words[words.length - 2].replace(",",""));
        }else if(Util.isFloatingNumber(lastWord)){
            price = Float.parseFloat(lastWord);
        }
        return price;
    }

    private TransactionReportCache getCachedTransactionReport(Pdf pdf) {
        CacheManager cacheManager = ReportsCacheManager.getManager();
        Object report = cacheManager.read(pdf.getFilePath());
        return report == null ? null : (TransactionReportCache) report;
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
        while (!lines[transactionStartIndex++].trim().equals(getTransactionEndIdentifier()) && transactionStartIndex < lines.length)
            ;
        return --transactionStartIndex;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    protected int getTransactionStartingIndex(String[] lines) {
        int i = 0;
        while (i < lines.length && !lines[i].trim().equals(getTransactionStartIdentifier())) {
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
