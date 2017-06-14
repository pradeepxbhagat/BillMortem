import expenditure.ExpenditureCalculator;
import org.junit.Assert;
import org.junit.Test;
import parser.HdfcBillParser;
import parser.Parser;
import pdf.PdfBoxReader;
import pdf.PdfReader;
import pdf.PdfReaderException;
import report.TransactionReport;
import transaction.TransactionSearch;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class ProjectTest {

    @Test
    public void testProject(){
        String fileUrl = "/Users/pp00344204/Documents/misc/padhai/projects/bill_reader/src/main/java/pdf/sample_password_protected_bill.pdf";
        PdfReader pdfReader = PdfBoxReader.getReader();
        String inPut = null;
        try {
            inPut = pdfReader.read(fileUrl,"PRAD0280");
        } catch (PdfReaderException e) {
            e.printStackTrace();
        }

        Parser parser = new HdfcBillParser();
        TransactionReport report = parser.parse(inPut);

        TransactionSearch search = TransactionSearch.getSearchEngine(report);
        TransactionReport searchedTransaction = search.searchTransaction("PATANJALI,PAYTM");
        double expenditure = ExpenditureCalculator.getCalculator().calculateTotalExpenditure(searchedTransaction);
        System.out.print(expenditure);
        Assert.assertTrue(expenditure+"testing complete",expenditure > 0);

    }
}
