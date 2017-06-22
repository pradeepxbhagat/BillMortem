package com.billmartam.pdf;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class PdfReaderException extends Exception {
    public PdfReaderException(int message) {
        super(message+"");
    }


    public int getExceptionType() {
        return Integer.parseInt(super.getMessage());
    }

    public static class ExceptionType{
        public static final int INVALID_PASSWORD = 1;
        public static final int PASSWORD_PROTECTED = 2;
        public static final int INVALID_PDF_FILE = 3;

    }
}
