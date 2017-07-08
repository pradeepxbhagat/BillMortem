package com.billmartam.pdf;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class PdfBoxReaderTest {

    @Test
    public void test_pdf_file() throws PdfReaderException {
        PdfReader pdfReader = PdfBoxReader.getReader();
        Pdf output = pdfReader.read("/Users/pp00344204/Desktop/sample.pdf");
        System.out.println(output);
        Assert.assertNotNull(output);
    }

    @Test
    public void test_not_a_file() throws PdfReaderException {
        PdfReader pdfReader = PdfBoxReader.getReader();
        Pdf output = pdfReader.read("/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/com.billmartam.pdf");
        System.out.println(output);
        Assert.assertNull(output);
    }

    @Test
    public void test_for_password_protected_Message() {
        PdfReader pdfReader = PdfBoxReader.getReader();
        try {
            pdfReader.read("/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/com.billmartam.pdf/sample_password_protected_bill.com.billmartam.pdf");
        } catch (PdfReaderException e) {
            Assert.assertEquals(PdfReaderException.ExceptionType.PASSWORD_PROTECTED,e.getExceptionType());
        }

    }

    @Test
    public void test_for_password_protected_file() {
        PdfReader pdfReader = PdfBoxReader.getReader();
        String path = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/res/citi_bank_cc_sample.pdf";
        Pdf output = null;
        try {
            output = pdfReader.read(path);
        } catch (PdfReaderException e) {
            try {
                output = pdfReader.read(path, "RAVI15JAN");
            } catch (PdfReaderException e1) {
                e1.printStackTrace();
            }
        }

        System.out.print(output.getData());
        Assert.assertNotNull(output);
    }

    @Test
    public void test_citi_pdf_file() {
        PdfReader pdfReader = PdfBoxReader.getReader();
        String path = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/res/citi_bank_cc_sample.pdf";
        Pdf output = null;
        try {
            output = pdfReader.read(path);
        } catch (PdfReaderException e) {
            try {
                output = pdfReader.read(path, "RAVI15JAN");
            } catch (PdfReaderException e1) {
                e1.printStackTrace();
            }
        }

//        System.out.print(output);
        Assert.assertNotNull(output);
    }

    @Test
    public void test_for_password_protected_wrong_password() {
        PdfReader pdfReader = PdfBoxReader.getReader();
        String path = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/com.billmartam.pdf/sample_password_protected_bill.com.billmartam.pdf";
        try {
            pdfReader.read(path, "password");
        } catch (PdfReaderException e) {
            Assert.assertEquals(PdfReaderException.ExceptionType.INVALID_PASSWORD, e.getExceptionType());
        }

    }

}
