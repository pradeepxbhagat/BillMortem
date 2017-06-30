package com.billmartam.parser;

/**
 * Created by pp00344204 on 30/06/17.
 */
public class BillIdentifier implements Identifier{
    private static final Object CITI_CC_IDENTIFIER = "CITIBANK";
    private static BillIdentifier identifier;

    public static Identifier getIdentifier() {
        if(identifier == null){
            identifier = new BillIdentifier();
        }
        return identifier;
    }

    @Override
    public BillVendor identify(String data) {
        return parseForIdentifier(data);
    }

    private BillVendor parseForIdentifier(String data) {
        if(isCtitBill(data)){
            return BillVendor.CITI;
        }
        return BillVendor.HDFC;
    }

    private boolean isCtitBill(String data) {
        if(isFirstWord(data).equals(CITI_CC_IDENTIFIER)){
            return true;
        }
        return false;
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
