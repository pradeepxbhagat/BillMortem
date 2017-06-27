package com.billmartam.parser;

/**
 * Created by pp00344204 on 27/06/17.
 */
public class HdfcBillParser extends BillParser implements Parser {

    @Override
    protected String getTransactionStartIdentifier() {
        return "Date  Transaction Description Amount (in Rs.)";
    }

    @Override
    protected String getTransactionEndIdentifier() {
        return "Reward Points Summary";
    }

    @Override
    protected int getTransactionStartingIndex(String[] lines) {
        int i = super.getTransactionStartingIndex(lines);
        return  i ==lines.length ? -1 : i+2;
    }
}
