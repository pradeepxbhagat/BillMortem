package com.billmartam.util;

import com.billmartam.TImeComplexityTestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 09/07/17.
 */
public class TextSearchTest extends TImeComplexityTestCase {

    @Test
    public void testSearch(){
        boolean isPresent = TextSearch.search("pradeep","pradeep this is a sample search term for pradeep pankaj search term");
        Assert.assertTrue(isPresent);
    }

    @Test
    public void testSearch1(){
        boolean isPresent = TextSearch.search("paytm","www.paytm.in");
        Assert.assertTrue(isPresent);
    }

    @Test
    public void testSearch2(){
        boolean isPresent = TextSearch.search("paytm","www.paytm.in");
        Assert.assertTrue(isPresent);
    }
}
