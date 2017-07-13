package com.billmartam.parser;

import com.billmartam.TImeComplexityTestCase;
import com.billmartam.pdf.Pdf;
import com.billmartam.report.TransactionReport;
import com.billmartam.transaction.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by pp00344204 on 27/06/17.
 */
public class HdfcBillParserTest extends TImeComplexityTestCase{
    @Test
    public void convertToLines(){
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        TransactionReport transactionReport = parser.parse(pdf,false);
        Assert.assertTrue(transactionReport.getContents().size() > 0);
    }

    @Test
    public void convert_to_lines_empty_data(){
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData("");
        TransactionReport transactionReport = parser.parse(pdf,false);
        Assert.assertNull(transactionReport);
    }

    @Test
    public void test_other_prdf_data(){
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData("this is other com.billmartam.pdf data");
        TransactionReport transactionReport = parser.parse(pdf,false);
        Assert.assertNull(transactionReport);
    }

    @Test
    public void test_cached_pdf1_null_test(){
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        pdf.setFilePath("root/folder1/folder2/file.pdf");
        TransactionReport transactionReport = parser.parse(pdf,true);
        Assert.assertNotNull("NULL test passed",transactionReport);
    }

    @Test
    public void test_cached_pdf_not_cached(){
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        pdf.setFilePath("root/folder1/folder2/file.pdf");
        TransactionReport transactionReport = parser.parse(pdf,true);
        Assert.assertNotNull(transactionReport);
    }

    @Test
    public void test_cached_pdf_cached(){
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        pdf.setFilePath("root/folder1/folder2/file.pdf");
        TransactionReport transactionReport = parser.parse(pdf,true);
        Assert.assertNotNull(transactionReport);
    }

    @Test
    public void test_transaction_model(){
        Parser parser = new HdfcBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);

        TransactionReport transactionReport = parser.parse(pdf,false);
//        List<Transaction> transaction = transactionReport.getContents();
        String result = "05/05/2017"+"www.vodafone.in        MUMBAI"+"356.50";
//        String actual = transaction.getDate()+transaction.getDiscription()+transaction.getPrice();

//        Assert.assertEquals(result,actual);
    }

    public final static String raw = "In case any of your personal details have changed, you can\n" +
            "update the same by contacting our Customer Service.\n" +
            " \n" +
            "Name : PRADEEP PANKAJ                          \n" +
            " \n" +
            "Email : PRADEEP.SYMBIOSIS@GMAIL.COM\n" +
            " \n" +
            "Address : INFOSTRECH SOLUTIONS PVT LTD\n" +
            "PRABHAVI TECH PARK 3RD FLOOR\n" +
            "BANER ROAD OPP IMC BLDG\n" +
            "PUNE-411045 MHS\n" +
            "Statement for Titanium Times Card Credit Card\n" +
            "Statement Date:05/06/2017     Card No: 5241 81XX XXXX 0280\n" +
            "Payment Due Date Total Dues Minimum Amount Due\n" +
            "25/06/2017 16,078.53 810.00\n" +
            "Credit Limit Available Credit Limit Available Cash Limit\n" +
            "1,71,000 1,53,067 68,400\n" +
            "Account Summary\n" +
            "Opening\n" +
            "Balance\n" +
            "Payment/ \n" +
            "Credits\n" +
            "Purchase/ \n" +
            "Debits\n" +
            "Finance\n" +
            "Charges\n" +
            "Total Dues\n" +
            "25,740.97 25,764.88 16,102.44 0.00 16,078.53\n" +
            "Past Dues (If any)\n" +
            "Overlimit 3 Months+ 2 Months 1 Month Current Dues\n" +
            "Minimum\n" +
            "Amount Due\n" +
            "0.00 0.00 0.00 0.00 810.00 810.00\n" +
            "IMPORTANT INFORMATION\n" +
            "1. PLEASE REVIEW THE CHANGES IN REWARD POINTS PROGRAM IN THE MITC U/S 9\n" +
            "OR CREDIT CARD PRODUCT PAGE ON BANK WEBSITE.\n" +
            "Domestic Transactions\n" +
            "Date  Transaction Description Amount (in Rs.)\n" +
            "    \n" +
            " PRADEEP PANKAJ   \n" +
            "05/05/2017 www.vodafone.in        MUMBAI 356.50  \n" +
            "05/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 33.00  \n" +
            "05/05/2017 SOUTH PARK CAFE & CANT PUNE 220.00  \n" +
            "05/05/2017 PAYTM APP              NOIDA 500.00  \n" +
            "06/05/2017 WWW OLACABS COM        GURGAON 100.00  \n" +
            "06/05/2017 BALAJI PETROLEUM       NAGPUR 511.50  \n" +
            "06/05/2017 HPCL 0.75% Cashless In MUMBAI 3.75 Cr\n" +
            "06/05/2017 PETRO SURCHARGE WAIVER 5.06 Cr\n" +
            "12/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 2.00  \n" +
            "12/05/2017 PAYTM APP              NOIDA 100.00  \n" +
            "12/05/2017 PAYTM APP              NOIDA 120.00  \n" +
            "12/05/2017 videocond2h_TPSL       MUMBA 500.00  \n" +
            "12/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 30.00  \n" +
            "13/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
            "13/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 8.00  \n" +
            "13/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 300.00  \n" +
            "13/05/2017 RADHA KRISHNA PURE VEG PUNE 185.00  \n" +
            "Titanium Times Card Credit Card Statement\n" +
            " Page 1 of 4\n" +
            "Domestic Transactions\n" +
            "Date  Transaction Description Amount (in Rs.)\n" +
            "    \n" +
            " PRADEEP PANKAJ   \n" +
            "15/05/2017 PAYTM APP              NOIDA 100.00  \n" +
            "16/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
            "17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00  \n" +
            "17/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
            "18/05/2017 PATANJALI              PUNE 760.00  \n" +
            "18/05/2017 PAYTM APP              NOIDA 44.00  \n" +
            "18/05/2017 VITALIFE HEALTH SERVICEPUNE 1,450.00  \n" +
            "18/05/2017 ACCLAIM HEALTH CARE PV PUNE 400.00  \n" +
            "18/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 30.00  \n" +
            "18/05/2017 PAYTM APP              NOIDA 100.00  \n" +
            "19/05/2017 ANIL JEWELLERS         PUNE 180.00  \n" +
            "19/05/2017 FARMERS & CROCERS      PUNE 127.00  \n" +
            "19/05/2017 RUCHIRA WINES          PUNE 280.00  \n" +
            "19/05/2017 GETWELL CHEMIST AND DRUPUNE 585.00  \n" +
            "19/05/2017 PAYTM APP              NOIDA 100.00  \n" +
            "19/05/2017 RACHIRA WINES          PUNE 170.00  \n" +
            "19/05/2017 TELE TRANSFER CREDIT 25,740.97 Cr\n" +
            "20/05/2017 RAJMANDIR ICECREAM BANEPUNE 120.00  \n" +
            "21/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 400.00  \n" +
            "22/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 5.00  \n" +
            "22/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 70.00  \n" +
            "22/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 33.00  \n" +
            "22/05/2017 AMITA ENTERPRISES A P  PUNE 514.37  \n" +
            "22/05/2017 PAYTM APP              NOIDA 20.00  \n" +
            "22/05/2017 PETRO SURCHARGE WAIVER 5.09 Cr\n" +
            "23/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
            "23/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
            "24/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 24.66  \n" +
            "24/05/2017 PAYTM APP              NOIDA 23.00  \n" +
            "25/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 60.00  \n" +
            "25/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 64.00  \n" +
            "25/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 35.00  \n" +
            "26/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 200.00  \n" +
            "26/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 85.00  \n" +
            "27/05/2017 PAYTM APP              NOIDA 45.00  \n" +
            "27/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 40.00  \n" +
            "27/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 50.00  \n" +
            "27/05/2017 AMITA ENTERPRISES A P  PUNE 343.33  \n" +
            "28/05/2017 MITUL ENTERPRISES PVT  MUMBAI 2,799.00  \n" +
            "28/05/2017 BURGER KING INDIA PVT LPUNE 92.58  \n" +
            "28/05/2017 PANCAKE FACTORY        PUNE 180.00  \n" +
            "29/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 100.00  \n" +
            "29/05/2017 SHREE SAI ENTERPRISES  PUNE 70.00  \n" +
            "30/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 10.00  \n" +
            "30/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 48.00  \n" +
            "30/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 35.00  \n" +
            "31/05/2017 PAYTM MOBILE SOLUT INR www.paytm.in 120.00  \n" +
            "31/05/2017 AUTHENTIC AESTHETIC CENPUNE 920.00  \n" +
            "31/05/2017 NEW PRASHANT ENTERPRIS PUNE 36.00  \n" +
            "01/06/2017 PAYTM MOBILE SOLUT INR www.paytm.in 130.00  \n" +
            "01/06/2017 PRABHU CATERERS        PUNE 45.00  \n" +
            "01/06/2017 MICROSOFT1291INRUSDB2C MSBILL.INFO 414.00  \n" +
            "01/06/2017 NEW PRASHANT ENTERPRIS PUNE 36.00  \n" +
            "01/06/2017 PAYTM MOBILE SOLUT INR www.paytm.in 2.00  \n" +
            "02/06/2017 PAYTM APP              NOIDA 15.00  \n" +
            "02/06/2017 SHELL INDIA MARKETS PV PUNE 1,011.50  \n" +
            "02/06/2017 PAYTM MOBILE SOLUT INR www.paytm.in 50.00  \n" +
            "   02/06/2017 PETRO SURCHARGE WAIVER 10.01 Cr\n" +
            "Reward Points Summary\n" +
            "Titanium Times Card Credit Card Statement\n" +
            " Page 2 of 4";


}
