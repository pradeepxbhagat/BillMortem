package com.billmartam.parser;

import com.billmartam.util.TextSearch;
import com.billmartam.util.Util;

/**
 * Created by pp00344204 on 27/06/17.
 */
public class CitiBillParser extends BillParser {

    @Override
    protected String getTransactionStartIdentifier() {
        return "Date Reference no Transaction Details Amount (in Rs)";
    }

    @Override
    protected String getTransactionEndIdentifier() {
        return "Rewards Everywhere, Every time";
    }

    @Override
    protected String isTransaction(String raw) {
        String[] words = raw.split(" ");
        return Util.isCitiDateFormat(words[0]) ? raw : null;
    }

    @Override
    protected boolean isCredit(String[] words) {
        String word = words[words.length -1];
        return TextSearch.search("CR",word);
    }

    @Override
    protected float getPrice(String[] words) {
        return isCredit(words)? Float.parseFloat(words[words.length -1].replace("CR","")) : Float.parseFloat(words[words.length -1]);
    }

    @Override
    protected String getDescription(String[] words) {
        int len = words.length -2;

        return getDescriptionString(words,len);
    }
}
