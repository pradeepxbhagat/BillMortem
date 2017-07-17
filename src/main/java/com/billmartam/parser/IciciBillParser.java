package com.billmartam.parser;

import com.billmartam.transaction.Transaction;
import com.billmartam.util.TextSearch;
import com.billmartam.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 17/07/17.
 */
public class IciciBillParser extends BillParser {

    @Override
    protected String getTransactionStartIdentifier() {
        return "Ref. Number";
    }

    @Override
    protected String getTransactionEndIdentifier() {
        return "Great offers on your card";
    }

    @Override
    protected List<Transaction> getTransactions(String[] lines, int transactionStartIndex, int transactionEndIndex) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = transactionStartIndex; i < transactionEndIndex; i++) {
            String transactionStr = isTransaction(lines[i].trim().toUpperCase());
            if (transactionStr != null) {
                Transaction transaction = null;
                if(hasOnlyDate(transactionStr)){
/*
                    205/07/2017
                    PAYTM MOBILE SOLUT INR www.paytm.in
                            IN
                    100.0085215227186726800829761
*/
                    StringBuilder builder = new StringBuilder();
                    builder.append(transactionStr);
                    i++;
                    while(!Util.isFloatingNumber(lines[i])){
                        builder.append(" ");
                        transactionStr = lines[i++].trim().toUpperCase();
                        builder.append(transactionStr);
                    }

                    builder.append(" ");
                    builder.append(lines[i].trim());

                    transactionStr = builder.toString();
                }

                transaction = getTransactionModel(transactionStr);

                if(transaction.getPrice() != -1) {
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

    private boolean hasOnlyDate(String transactionStr) {
        return transactionStr.split(" ").length == 1;
    }

    @Override
    protected String isTransaction(String raw) {
        String[] words = raw.split(" ");
        return Util.isIciciDateFormat(words[0]) ? raw : null;
    }

    protected boolean isCredit(String[] words) {
        return TextSearch.match("CR", words[words.length - 1]);
    }

    protected float getPrice(String[] words) {
        float price = -1;
        String lastWord = words[words.length - 1].replace(",","");
        if(isCredit(words)){
            price = Float.parseFloat(words[words.length - 2].replace(",",""));
        }else if(Util.isFloatingNumber(lastWord)){
            String[] lastWords = lastWord.split("\\.");
            price = Float.parseFloat(lastWords[0]+"."+lastWords[1].charAt(0)+""+lastWords[1].charAt(1));
        }
        return price;
    }
}
