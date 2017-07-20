package com.billmortam.pdf;

/**
 * Created by pp00344204 on 06/06/17.
 */
public interface PdfReader {
    Pdf read(String url) throws PdfReaderException;

    Pdf read(String path, String password) throws PdfReaderException;
}
