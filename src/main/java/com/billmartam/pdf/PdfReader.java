package com.billmartam.pdf;

/**
 * Created by pp00344204 on 06/06/17.
 */
public interface PdfReader {
    String read(String url) throws PdfReaderException;

    String read(String path, String password) throws PdfReaderException;
}
