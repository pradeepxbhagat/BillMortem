package billmortam.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
        boolean result = Util.isHdfcDateFormat("5/05/2017");
        Assert.assertTrue(result);
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

    @Test
    public void collection_join() throws Exception {
        Set<String> keys = new HashSet<>();
        keys.add("pradeep");
        keys.add("pankaj");
        keys.add("neha");

        String actual = "pradeep,neha,pankaj";

        Assert.assertEquals(actual, Util.join(keys));
    }

    @Test
    public void test_citi_date_format() {
        boolean result = Util.isCitiDateFormat("5/05");
        Assert.assertTrue(result);
    }

    @Test
    public void ignore_key_test() {
        boolean result = Util.inIgnoreKeyList("IN");
        Assert.assertTrue(result);
    }

    @Test
    public void test_icici_transaction() {
        boolean result = Util.isIciciDateFormat("14/07/2017");
        Assert.assertTrue(result);
    }

    @Test
    public void test_icici_transaction1() {
        boolean result = Util.isIciciDateFormat("214/07/2017");
        Assert.assertTrue(result);
    }

}