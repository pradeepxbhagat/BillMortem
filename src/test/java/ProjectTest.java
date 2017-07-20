import com.billmortam.expenditure.ExpenditureCalculator;
import com.billmortam.pdf.Pdf;
import org.junit.Assert;
import org.junit.Test;
import com.billmortam.parser.HdfcBillParser;
import com.billmortam.parser.Parser;
import com.billmortam.pdf.PdfBoxReader;
import com.billmortam.pdf.PdfReader;
import com.billmortam.pdf.PdfReaderException;
import com.billmortam.report.TransactionReport;
import com.billmortam.transaction.TransactionSearch;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class ProjectTest {

    @Test
    public void testProject(){
        String fileUrl = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/resources/res/sample_pdfs/sample_password_protected_bill.pdf";
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
