package com.billmartam.parser;

import com.billmartam.pdf.Pdf;
import com.billmartam.util.TextSearch;

/**
 * Created by pp00344204 on 30/06/17.
 */
public class BillIdentifier implements Identifier{
    private static final String CITI_CC_IDENTIFIER = "CITIBANK";
    private static BillIdentifier identifier;

    public static Identifier getIdentifier() {
        if(identifier == null){
            identifier = new BillIdentifier();
        }
        return identifier;
    }

    @Override
    public BillVendor identify(Pdf data) {
        return parseForIdentifier(data);
    }

    private BillVendor parseForIdentifier(Pdf data) {
        if(isCtitBill(data)){
            return BillVendor.CITI;
        }
        return BillVendor.HDFC;
    }

    private boolean isCtitBill(Pdf data) {
        /*if(isFirstWord(data.getData()).equals(CITI_CC_IDENTIFIER)){
            return true;
        }*/
        return TextSearch.search(CITI_CC_IDENTIFIER,data.getData());
    }

    private String isFirstWord(String data) {
        int i =0;
        String result = "";
        char c = data.charAt(i);
        while(c != ' '){
            result +=c;
            c = data.charAt(++i);
        }
        return result;
    }
}
