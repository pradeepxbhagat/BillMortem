package com.billmartam.parser;

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
}
