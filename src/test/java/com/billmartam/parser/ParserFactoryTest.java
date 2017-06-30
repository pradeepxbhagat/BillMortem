package com.billmartam.parser;

import com.billmartam.TImeComplexityTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 30/06/17.
 */
public class ParserFactoryTest extends TImeComplexityTestCase{
    //fetch hdfc bill parser
    @Test
    public void fetchHdfcBillParser(){
        ParserFactory factory = ParserFactory.getFactory();
        Parser parser = factory.getParser(BillVendor.HDFC);
        Assert.assertTrue(parser instanceof HdfcBillParser);
    }

    //fetch citi bill parser
    @Test
    public void fetchCitiBillParser(){
        ParserFactory factory = ParserFactory.getFactory();
        Parser parser = factory.getParser(BillVendor.CITI);
        Assert.assertTrue(parser instanceof CitiBillParser);
    }

    //test from raw text for hdfc vendor
    @Test
    public void fetchHdfcBillParserFromRawtext(){
        Identifier identifier = BillIdentifier.getIdentifier();
        ParserFactory factory = ParserFactory.getFactory();
        Parser parser = factory.getParser(identifier.identify(HdfcBillParserTest.raw));
        Assert.assertTrue(parser instanceof HdfcBillParser);
    }

    //test from raw text for citi vendor
    @Test
    public void fetchCitiBillParserFromRawtext(){
        Identifier identifier = BillIdentifier.getIdentifier();
        ParserFactory factory = ParserFactory.getFactory();
        Parser parser = factory.getParser(identifier.identify(CitiBillParserTest.raw));
        Assert.assertTrue(parser instanceof CitiBillParser);
    }
}