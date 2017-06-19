package util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class UtilTest {
    @Test
    public void test_is_floating_number() {
        boolean result = Util.isFloatingNumber("3.30");
        Assert.assertTrue(result);

    }

    @Test
    public void test_is_int_number() {
        boolean result = Util.isFloatingNumber("3");
        Assert.assertFalse(result);
    }

    @Test
    public void test_pdf_url() {
        boolean result = Util.checkPdfUrl("/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf/sample.pdf");
        Assert.assertTrue(result);
    }

    @Test
    public void test_pdf_url_negative() {
        boolean result = Util.checkPdfUrl("/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf/");
        Assert.assertFalse(result);
    }

    @Test
    public void test_pdf_url_forwardslash() {
        boolean result = Util.checkPdfUrl("src\\main\\java\\pdf\\sample.pdf");
        Assert.assertTrue(result);
    }

    @Test
    public void test_pdf_url_forwardslash_negative() {
        boolean result = Util.checkPdfUrl("src\\main\\java\\pdf\\");
        Assert.assertFalse(result);
    }

    @Test
    public void test_is_date() {
        boolean result = Util.isDate("5/05/2017");
        Assert.assertFalse(result);
    }

    @Test
    public void test_replaceSpaceInStringWithComma() {
        String result = Util.replaceSpaceInStringWithComma("pradeep pankaj");
        Assert.assertEquals("pradeep,pankaj",result);
    }

    @Test
    public void test_getCommaSplittedString() {
        String[] result = Util.getCommaSplittedString("pradeep,pankaj",",");
        Assert.assertArrayEquals(new String[]{"pradeep","pankaj"},result);
    }


}