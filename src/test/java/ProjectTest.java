import com.billmartam.expenditure.ExpenditureCalculator;
import com.billmartam.pdf.Pdf;
import org.junit.Assert;
import org.junit.Test;
import com.billmartam.parser.HdfcBillParser;
import com.billmartam.parser.Parser;
import com.billmartam.pdf.PdfBoxReader;
import com.billmartam.pdf.PdfReader;
import com.billmartam.pdf.PdfReaderException;
import com.billmartam.report.TransactionReport;
import com.billmartam.transaction.TransactionSearch;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class ProjectTest {

    @Test
    public void testProject(){
        String fileUrl = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/res/sample_password_protected_bill.pdf";
        PdfReader pdfReader = PdfBoxReader.getReader();
        Pdf inPut = null;
        try {
            inPut = pdfReader.read(fileUrl,"1234");
        } catch (PdfReaderException e) {
            e.printStackTrace();
        }

        Parser parser = new HdfcBillParser();
        TransactionReport report = parser.parse(inPut,false);

        TransactionSearch search = TransactionSearch.getSearchEngine(report);
        TransactionReport searchedTransaction = search.searchTransaction("PATANJALI,PAYTM");
        double expenditure = ExpenditureCalculator.getCalculator().calculateTotalExpenditure(searchedTransaction);
        System.out.print(expenditure);
        Assert.assertTrue(expenditure+"testing complete",expenditure > 0);

    }
}
