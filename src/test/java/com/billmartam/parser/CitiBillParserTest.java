package com.billmartam.parser;

import com.billmartam.pdf.Pdf;
import com.billmartam.report.TransactionReport;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 27/06/17.
 */
public class CitiBillParserTest {

    @Test
    public void parse_citi_bill(){
        Parser parser = new CitiBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        TransactionReport parse = parser.parse(pdf,false);
        Assert.assertNotNull(parse);
    }

    @Test
    public void transaction_size_test(){
        Parser parser = new CitiBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        TransactionReport parse = parser.parse(pdf,false);
        Assert.assertTrue(parse.getContents().size() > 0);
    }

    @Test
    public void check_parsed_transaction_correctness(){
        Parser parser = new CitiBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        TransactionReport parse = parser.parse(pdf,false);
        System.out.println(parse.getContents().get(0).toString());
        System.out.println(parse.getContents().get(3).toString());
        Assert.assertTrue(parse.getContents().get(0).toString().equals("22/04 74332747113 PLATINUM SUPER STORE     PUNE 405.0") && parse.getContents().get(parse.getContents().size() - 1).toString().equals("15/05 74766517135 SHELL INDIA MARKETS PV   PUNE 311.5"));
    }

    public static String raw = "CITIBANK REWARDS PLATINUM CARD \n" +
            "Statement for Citibank Card Number ************3872 \n" +
            "Statement Period:  24 April 2017 to 21 May 2017 \n" +
            " At a glance \n" +
            "  ___________________________________________________    \n" +
            "Statement Date:   \n" +
            "21/05/17   \n" +
            "  ___________________________________________________   \n" +
            "Total Amount Due:   \n" +
            "Rs.4834.58    \n" +
            "  ___________________________________________________   Minimum Amount Due:   Rs.241.73     \n" +
            "  ___________________________________________________   Due Date:   07/06/17      \n" +
            "___________________________________________________   \n" +
            "Credit Limit:   \n" +
            "Rs.94000.00   \n" +
            "  ___________________________________________________   \n" +
            "Available Credit Limit:   \n" +
            "Rs.88316.00   \n" +
            "  ___________________________________________________   \n" +
            "Available Cash Limit   \n" +
            "Rs.10000.00   \n" +
            " Account Summary \n" +
            "  ___________________________________________________    \n" +
            "Previous balance:   \n" +
            "Rs.45576.08   \n" +
            "  ___________________________________________________   \n" +
            "Current Purchases & Other Charges:   \n" +
            "Rs.4838.50    \n" +
            "  ___________________________________________________   \n" +
            "Current Cash Advance:   \n" +
            "Rs.0.00       \n" +
            "  ___________________________________________________   \n" +
            "Last Payments Received:   \n" +
            "Rs.45580.00   \n" +
            "  ___________________________________________________   \n" +
            "Points Earned:   \n" +
            "39        \n" +
            " \n" +
            "Update Contact Details \n" +
            "  ___________________________________________________  \n" +
            " \n" +
            "Email id: \n" +
            "RAVIXKUMAR90@GMAIL.COM \n" +
            " \n" +
            "To update your contact details, login to\n" +
            "www.citibank.com/india with your Internet\n" +
            "Password (IPIN) and click on the links\n" +
            "under \"Manage your account\". \n" +
            " \n" +
            "To self-select IPIN (Internet Password)\n" +
            "instantly Click here \n" +
            " \n" +
            "Pay Online : \n" +
            " \n" +
            "- Citibank Online      - E-Pay/NEFT \n" +
            " \n" +
            "Highlights \n" +
            "  ___________________________________________________  \n" +
            " \n" +
            " \n" +
            "Did you know that this statement also contains details on\n" +
            "Your Reward Points____________________________________________ \n" +
            " \n" +
            "Convert your big shopping transactions into small, manageable EMIs! Click on the highlighted transaction, login to Citibank Online\n" +
            "and convert into an EMI now! T&C apply. \n" +
            "Note: Please disable popup blocker to allow pop-ups from Citibank India\n" +
            "Detailed Statement  \n" +
            " \n" +
            "Date Reference no Transaction Details Amount (in Rs)\n" +
            "22/04 74332747113 PLATINUM SUPER STORE     PUNE 405.00\n" +
            "26/04 74332747117 RICHFEEL                 PUNE 1250.00\n" +
            "29/04 74766517120 APEKSHA BEER SHOPEE      PUNE 450.50\n" +
            "29/04 VT171190178 E Pay IHDF5325328376170428 45580.00CR\n" +
            "01/05 74505147121 AMITA ENTERPRISES A P    PUNE 311.50\n" +
            " \n" +
            " \n" +
            "Date Reference no Transaction Details Amount (in Rs)\n" +
            "05/05 74311197126 RACHIRA WINES            PUNE 583.00\n" +
            "05/05 74766517125 FARMERS & CROCERS        PUNE 142.00\n" +
            "06/05 74332747127 RUCHIRA WINES            PUNE 583.00\n" +
            "13/05 74311197135 RACHIRA WINES            PUNE 438.00\n" +
            "14/05 74332747135 PLATINUM SUPER STORE     PUNE 253.00\n" +
            "14/05 74766517134 FARMERS & CROCERS        PUNE 111.00\n" +
            "15/05 74766517135 SHELL INDIA MARKETS PV   PUNE 311.50\n" +
            "Rewards Everywhere, Every time \n" +
            "_____________________________________________________________________________________________________________________________  \n" +
            "Your Reward Points Summary \n" +
            "Points earned so far Points earned this month Points redeemed this month Points available for redemption\n" +
            "1699      39        0         1738      _____________________________________________________________________________________________________________________________________________________________________________________ \n" +
            "Explore the world of Citibank Rewards  to redeem from a wide variety of options ^TOP\n" +
            "______________________________________________________________________________________________________________________________________________________________________________________\n" +
            "To know how to redeem your Reward points, Click on REDEEM REWARDS tab on  this page \n" +
            "______________________________________________________________________________________________________________________________________________________________________________________\n" +
            "Other offers on your Card:\n" +
            " \n" +
            "Food & Beverages \n" +
            " \n" +
            "Shop Online  Others \n" +
            "Enjoy new cuisines and great savings\n" +
            "only with your Citi Credit Card at over\n" +
            "2000 of your favourite restaurants in\n" +
            "Bengaluru,Chennai,Delhi/NCR,Mumbai,\n" +
            "Hyderabad and Pune. \n" +
            "Know more.\n" +
            "Choose to pay in easy EMIs at leading\n" +
            "online retailers such as flipkart.com,\n" +
            "homeshop18.com and ebay.in\n" +
            "Now redeem your Rewards Points for\n" +
            "Hot Deals on watches, perfumes,\n" +
            "cameras and much more on the\n" +
            "exc lus ive Rewards Merchandise\n" +
            "websi te .   \n" +
            "Click here to know more and start\n" +
            "redeeming now!\n" +
            ".^TOP\n" +
            "Important Information\n" +
            "The \"Available Credit Limit\" shown in this statement takes into account charges incurred but not due. Please ensure that\n" +
            "at least the \"Minimum Amount Due\" reaches us by the \"Due Date\".\n" +
            "If the minimum amount due or part amount less than the total amount due is paid, interest charges are applicable\n" +
            "(including fresh purchases, if any) on an average daily reducing balance method.\n" +
            "For charges related to your Credit Card, please  click here\n" +
            "Under an initiative of the Government of India and the Reserve Bank of India (RBI), to improve functionality and stability\n" +
            "of the Indian financial system, all banks and financial institutions are required to share customer data with Credit\n" +
            "Information Companies (CICs). CICs are  repository of information shared by banks, NBFC etc. and they  collect,\n" +
            "maintain and provide credit information on all borrowers to financial institutions. To know more about CICs, please click\n" +
            "here. \n" +
            "To know the Voluntary Codes as prescribed by the \"The Banking Codes and Standards Board of India (BCSBI)\" in any\n" +
            "Indian language of your choice, please click here \n" +
            "As you may be aware that trading in foreign exchange (including through electronic/internet trading portals) is prohibited\n" +
            "under the Foreign Exchange Management Act (FEMA), 1999.  Further, the Reserve Bank of India (RBI) has clarified\n" +
            "many a time that remittance in any form towards overseas foreign exchange trading (including through\n" +
            "electronic/internet trading portals or by use of credit/debit cards) is not permitted. RBI has cautioned the public not to\n" +
            "remit or deposit money or utilize credit/debit cards for such unauthorised transactions. The advice has become\n" +
            "necessary in the wake of many unauthorized transactions being conducted by residents that have been reported to RBI.\n" +
            "Accordingly, please take note of the prohibitions on using your credit/debit card for such unauthorized transactions in\n" +
            "contravention to the  FEMA & regulations thereunder and that violation(s), if any, would attract penalties or as RBI or the\n" +
            "Enforcement Directorate may initiate against such residents.\n" +
            "In case of non-payment, our Customer Assistance Specialists, engaged through our authorized agencies may contact\n" +
            "you. To view the complete list of our authorised agencies, please  Click here  \n" +
            "Please note that as per extant RBI regulation, debit from NRO A/c towards settlement of International charges on\n" +
            "International Credit Cards is now subject to below restrictions on repatriation of NRO A/c balances: \n" +
            "A Non-Resident Indian (NRI) or a Person of Indian Origin (PIO) may remit an amount up to USD one million, per\n" +
            "financial year, out of the balances held in his Non- Resident (Ordinary) Rupee (NRO) account, for all bona fide\n" +
            "purposes, subject to payment of applicable taxes in India, if any. Further, any repatriation from NRO A/c is subject to\n" +
            "payment of applicable taxes in India.\n";
}
