package com.billmartam.parser;

import com.billmartam.TImeComplexityTestCase;
import com.billmartam.pdf.Pdf;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 30/06/17.
 */
public class BillIdentifierTest extends TImeComplexityTestCase{

    //Testing hdfc bill type
    @Test
    public void identifyHdfcBillType(){
        Identifier identifier = BillIdentifier.getIdentifier();
        Pdf pdf = new Pdf();
        pdf.setData(HdfcBillParserTest.raw);
        BillVendor type = identifier.identify(pdf);

        Assert.assertTrue(type == BillVendor.HDFC);
    }

    //identify citi bill type
    @Test
    public void identifyCitiBillType(){
        Identifier identifier = BillIdentifier.getIdentifier();
        Pdf pdf = new Pdf();
        pdf.setData(CitiBillParserTest.raw);
        BillVendor type = identifier.identify(pdf);

        Assert.assertTrue(type == BillVendor.CITI);
    }
}
