package com.billmartam.parser;

/**
 * Created by pp00344204 on 30/06/17.
 */
public class ParserFactory {
    private static ParserFactory factory;

    public synchronized static ParserFactory getFactory() {
        if(factory == null){
            factory = new ParserFactory();
        }
        return factory;
    }

    public Parser getParser(BillVendor vendor) {
        switch (vendor){
            case HDFC: return new HdfcBillParser();
            case CITI: return new CitiBillParser();
        }
        return null;
    }
}
