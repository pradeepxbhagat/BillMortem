package billmortam.parser;

import billmortam.pdf.Pdf;
import billmortam.report.TransactionReport;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 17/07/17.
 */
public class IciciBillParserTest {

    @Test
    public void testIciciBillInstance() throws Exception {
        Parser parser = new IciciBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        pdf.setFilePath("some file path");
        TransactionReport report = parser.parse(pdf,false);
        Assert.assertNotNull(report);
    }

    @Test
    public void parsed_transaction_size_greater_then_0() throws Exception {
        Parser parser = new IciciBillParser();
        Pdf pdf = new Pdf();
        pdf.setData(raw);
        pdf.setFilePath("some file path");
        TransactionReport report = parser.parse(pdf,false);
        Assert.assertTrue(report.getContents().size() == 44);
    }

    public static final String raw = "|\n" +
            "Interest will be levied if Total\n" +
            "Amount due is not paid\n" +
            "02/08/2017\n" +
            "900.00\n" +
            "Minimum Amount Due\n" +
            "|\n" +
            "Statement\n" +
            "Summary\n" +
            "Previous Balance Purchases/ Charges Cash Advances Payments / Credits\n" +
            "0.00 6,747.85\n" +
            "Credit\n" +
            "Summary\n" +
            "Credit Limit Available Credit\n" +
            "32,119.2750,000.00\n" +
            "+ + -10,366.88 14,195.70\n" +
            "Convert your Big shopping bills into small, manageable EMI's! If any of the below transaction is highlighted,you may login to ICICI Bank\n" +
            "Internet Banking and convert into EMI now ! TnC apply, visit www.icicibank.com/emioncall for details.\n" +
            "17,814.73\n" +
            "MR. ABHISHEK MRINALKANTI GHOSH\n" +
            "Your Total Amount Due\n" +
            "5241 XXXX XXXX 4028\n" +
            "14/07/2017\n" +
            "Statement Date\n" +
            "Due Date :\n" +
            "Customer Name  Card Account No\n" +
            "=\n" +
            "Points Earned\n" +
            "REWARDS\n" +
            "             354             354\n" +
            "Points Transferred to PAYBACK (Acc:9401158534211208)\n" +
            "TRANSACTION DETAILSI  I 1574140700171924INVOICE_NO :\n" +
            "Card Number : 0000 XXXX XXXX 4814  -\n" +
            "Reward\n" +
            "Points\n" +
            "Date Transaction Details Amount(inCurrency\n" +
            "International\n" +
            "amount |)\n" +
            "Ref. Number\n" +
            "14/07/2017 SGST-CI@9% 50.57\n" +
            "14/07/2017 CGST-CI@9% 50.57\n" +
            "14/07/2017 Interest Charges 561.90\n" +
            "Card Number : 5241 XXXX XXXX 4028  -  ABHISHEK GHOSH\n" +
            "Reward\n" +
            "Points\n" +
            "Date Transaction Details Amount(inCurrency\n" +
            "International\n" +
            "amount |)\n" +
            "Ref. Number\n" +
            "617/06/2017\n" +
            "DR KUNALS DIAGNOSTIC C PIMPALE\n" +
            "SAUDA IN\n" +
            "300.0075298127169000060047454\n" +
            "10017/06/2017 KOTHARI ENDOSCOPY CENT PUNE IN 5,000.0075503727169013832386108\n" +
            "7717/06/2017 BIG BAZAAR PUNE IN 1,927.6985215227168723709519801\n" +
            "-1419/06/2017 MAKEMYTRIP INDIA PVT L NEW DELHI IN 684.00 CR75260827171013872380638\n" +
            "20/06/2017 HPCL 0.75% Cashless In MUMBAI IN 3.75 CR85215227172724935163624\n" +
            "1020/06/2017 RAJMUDRA PETROLEUM PUNE IN 511.5085215227171724908654394\n" +
            "22/06/2017 Service tax reversal 1.50 CR\n" +
            "22/06/2017 Rev of HPCL Surcharge Rev 10.00 CR\n" +
            "TRANSACTION DETAILSI  I 1574140700171924INVOICE_NO :\n" +
            "Card Number : 5241 XXXX XXXX 4028  -  ABHISHEK GHOSH\n" +
            "Reward\n" +
            "Points\n" +
            "Date Transaction Details Amount(inCurrency\n" +
            "International\n" +
            "amount |)\n" +
            "Ref. Number\n" +
            "26/06/2017 HPCL 0.75% Cashless In MUMBAI IN 3.75 CR85215227178725334795928\n" +
            "1026/06/2017 RAJMUDRA PETROLEUM PUNE IN 511.5085215227177725297372997\n" +
            "226/06/2017 GAUR NITAI TRUST PUNE IN 105.0075265507178000082148190\n" +
            "7027/06/2017 BIG BAZAAR PUNE IN 1,761.2385215227178725340769206\n" +
            "28/06/2017 Rev of HPCL Surcharge Rev 10.00 CR\n" +
            "28/06/2017 Service tax reversal 1.50 CR\n" +
            "29/06/2017 HPCL 0.75% Cashless In MUMBAI IN 2.25 CR85215227181725503020228\n" +
            "629/06/2017 RAJMUDRA PETROLEUM PUNE IN 311.5085215227180725469613314\n" +
            "30/06/2017 CLICK TO PAY PAYMENT RECEIVED 4,000.00 CR\n" +
            "2030/06/2017 RAJMUDRA PETROLEUM PUNE IN 1,011.5085215227181725535471290\n" +
            "30/06/2017 HPCL 0.75% Cashless In MUMBAI IN 7.50 CR85215227182725599215947\n" +
            "5101/07/2017 FUTURE RETAIL LTD. PUNE IN 1,263.7005450807183006053810808\n" +
            "02/07/2017 Rev of HPCL Surcharge Rev 10.00 CR\n" +
            "02/07/2017 SGST-Rev-CI@9% 0.90 CR\n" +
            "02/07/2017 CGST-Rev-CI@9% 0.90 CR\n" +
            "02/07/2017 Rev of HPCL Surcharge Rev 10.00 CR\n" +
            "02/07/2017 SGST-Rev-CI@9% 0.90 CR\n" +
            "02/07/2017 CGST-Rev-CI@9% 0.90 CR\n" +
            "402/07/2017 GOBIND ENTERPRISES PUNE IN 215.0475265507184000085897050\n" +
            "03/07/2017 CLICK TO PAY PAYMENT RECEIVED 2,000.00 CR\n" +
            "205/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "100.0085215227186726800829761\n" +
            "106/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "60.0085215227187726855333197\n" +
            "06/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "20.0085215227187726854724065\n" +
            "206/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "120.0085215227187726871958241\n" +
            "107/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "60.0085215227188726911280787\n" +
            "07/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "23.0085215227188726904943516\n" +
            "107/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "33.0085215227188726904966673\n" +
            "110/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "63.0085215227191727096720691\n" +
            "112/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "30.0085215227193727229213455\n" +
            "12/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "15.0085215227193727231567864\n" +
            "112/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "30.0085215227193727226617260\n" +
            "112/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "33.0085215227193727227055510\n" +
            "112/07/2017\n" +
            "PAYTM MOBILE SOLUT INR www.paytm.in\n" +
            "IN\n" +
            "27.0085215227193727225614227\n" +
            "Great offers on your card\n" +
            "Bon Appétit EntertainmentBon Voyage Gadgets & More\n" +
            " to know more\n" +
            "Enjoy deals and discounts\n" +
            "on travel and hotel\n" +
            "accommodation when you\n" +
            "pay with your ICICI Bank\n" +
            "Credit Card on our partner\n" +
            "websites.\n" +
            "Click here\n" +
            "Enjoy minimum 15%\n" +
            "savings on your dining bills\n" +
            "across 800 restaurants in\n" +
            "11 cities.\n" +
            " to know moreClick here\n" +
            "Get free tickets and\n" +
            "discounts when you buy\n" +
            "movie show tickets with\n" +
            "your ICICI Bank Credit\n" +
            "Card.\n" +
            " to know moreClick here\n" +
            "Enjoy deals and discounts\n" +
            "on electronic gadgets\n" +
            "when you buy with your\n" +
            "ICICI Bank Credit Card at\n" +
            "our partner outlets.\n" +
            " to know moreClick here\n" +
            "Spotlight Deals\n" +
            "Important Messages\n" +
            "Safety Tips - Do not transact if you find a suspicious device attached to the ATM machine. Avoid taking help from strangers at\n" +
            "an ATM.\n" +
            "Effective July 1, 2017 the Goods and Services Tax (GST) will be applicable in place of Service Tax.\n" +
            "GST Registration number: Applied for *\n" +
            "HSN code for service: 9971 - Financial and related services\n" +
            "Registered office address: ICICI Bank Tower, Ground Floor, BKC Road , Bandra Kurla Complex, Mumbai Suburban\n" +
            "Maharashtra - 400051.\n" +
            "Making only a minimum payment every month would result in repayment stretching over years with consequent interest\n" +
            "payment on the outstanding balance.\n" +
            "* ICICI Bank is registered for Banking services under GST and applied for separate registration number for credit card business vertical\n" +
            "Sudipta Roy\n" +
            "For ICICI Bank Limited.\n" +
            "Head – Consumer Cards\n" +
            "This is an authenticated intimation/statement.Customers are requested to immediately notify the Bank of any discrepancy in the statement\n" +
            "The category of service:Credit & Debit Card,Charge Card or Other payment Card service.\n" +
            "RegistrationNo.Mum/Div -III/ST/CDR/23. PAN based STC No:AAACI1195HST001.\n" +
            "REGISTERED OFFICE IS ICICI Bank Tower, Ground Floor, BKC Road , Bandra Kurla Complex, Mumbai Suburban Maharashtra  - 400051.";
}
