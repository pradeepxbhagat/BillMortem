package pdf;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class PdfBoxReaderTest {

    @Test
    public void test_pdf_file() throws PdfReaderException {
        PdfReader pdfReader = PdfBoxReader.getReader();
        String output = pdfReader.read("/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf/sample.pdf");
        System.out.println(output);
        Assert.assertNotNull(output);
    }

    @Test
    public void test_not_a_file() throws PdfReaderException {
        PdfReader pdfReader = PdfBoxReader.getReader();
        String output = pdfReader.read("/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf");
        System.out.println(output);
        Assert.assertNull(output);
    }

    @Test
    public void test_for_password_protected_Message() {
        PdfReader pdfReader = PdfBoxReader.getReader();
        try {
            pdfReader.read("/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf/sample_password_protected_bill.pdf");
        } catch (PdfReaderException e) {
            Assert.assertEquals(PdfReaderException.ExceptionType.PASSWORD_PROTECTED,e.getExceptionType());
        }

    }

    @Test
    public void test_for_password_protected_file() {
        PdfReader pdfReader = PdfBoxReader.getReader();
        String path = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf/sample_password_protected_bill.pdf";
        String output = null;
        try {
            output = pdfReader.read(path);
        } catch (PdfReaderException e) {
            try {
                output = pdfReader.read(path, "PRAD0280");
            } catch (PdfReaderException e1) {
                e1.printStackTrace();
            }
        }

        System.out.print(output);
        Assert.assertNotNull(output);
    }

    @Test
    public void test_for_password_protected_wrong_password() {
        PdfReader pdfReader = PdfBoxReader.getReader();
        String path = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf/sample_password_protected_bill.pdf";
        try {
            pdfReader.read(path, "password");
        } catch (PdfReaderException e) {
            Assert.assertEquals(PdfReaderException.ExceptionType.INVALID_PASSWORD, e.getExceptionType());
        }

    }

}
