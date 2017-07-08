package com.billmartam.cache;

import com.billmartam.TImeComplexityTestCase;
import com.billmartam.report.TransactionReport;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp00344204 on 03/07/17.
 */
public class ReportsCacheManagerTest extends TImeComplexityTestCase{
    @Test
    public void test_report_saving(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("temp1");

        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        Assert.assertTrue(cacheManager.save(reportCache));
    }

    @Test
    public void test_report_reading(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("temp1");

        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("temp1");
        Assert.assertTrue(result.getReport().getContents().size() == 4);
    }

    @Test
    public void test_report_overwriting_file(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("temp1");

        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("temp1");
        Assert.assertTrue(result.getReport().getContents().size() == 11);
    }

    @Test
    public void test_report_writing_file(){
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/file.pdf");

        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/file.pdf");
        Assert.assertTrue(result.getReport().getContents().size() == 11);
    }

    @Test
    public void test_report_writing_file1() {
        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/file1.pdf");

        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("18/05/2017 PATANJALI              PUNE 760.00 ");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/file1.pdf");
        Assert.assertTrue(result.getReport().getContents().size() == 10);
    }

    // test for saving password protected file
    @Test
    public void test_report_reading_password_file1() {
        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/password_file1.pdf");
        reportCache.setReport(transactionReport);
        reportCache.setProtectionKey("Pradeep@123");

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/password_file1.pdf");
        Assert.assertTrue(result.getProtectionKey().equals("Pradeep@123"));
    }

    @Test
    public void test_report_reading_unprotected_file() {
        List<String> contents = new ArrayList<>();
        contents.add("15/05/2017 PAYTM APP              NOIDA 100.00");
        contents.add("16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  ");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        contents.add("17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00");
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setContents(contents);

        TransactionReportCache reportCache = new TransactionReportCache();
        reportCache.setFileName("folder1/folder2/folder3/password_file1.pdf");
        reportCache.setReport(transactionReport);

        CacheManager cacheManager = ReportsCacheManager.getManager();
        cacheManager.save(reportCache);

        TransactionReportCache result = (TransactionReportCache) cacheManager.read("folder1/folder2/folder3/password_file1.pdf");
        Assert.assertNull(result.getProtectionKey());
    }
}
