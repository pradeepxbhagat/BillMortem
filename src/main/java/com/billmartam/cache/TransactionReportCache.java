package com.billmartam.cache;

import com.billmartam.report.TransactionReport;

import java.io.Serializable;

/**
 * Created by pp00344204 on 03/07/17.
 */
public class TransactionReportCache implements Serializable{
    private TransactionReport transactionReport;
    private String fileName;
    private String key;

    public TransactionReportCache() {
    }

    public TransactionReportCache(TransactionReport report, String filePath) {
        this.transactionReport = report;
        this.fileName = filePath;
    }

    public void setReport(TransactionReport transactionReport) {
        this.transactionReport = transactionReport;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TransactionReport getReport() {
        return transactionReport;
    }

    public void setProtectionKey(String key) {
        this.key = key;
    }

    public String getProtectionKey() {
        return key;
    }
}
