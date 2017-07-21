
import billmortam.expenditure.ExpenditureCalculator;
import billmortam.parser.HdfcBillParser;
import billmortam.parser.Parser;
import billmortam.pdf.Pdf;
import billmortam.pdf.PdfBoxReader;
import billmortam.pdf.PdfReader;
import billmortam.pdf.PdfReaderException;
import billmortam.report.TransactionReport;
import billmortam.transaction.TransactionSearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class ProjectTest {

    @Test
    public void testProject(){
        String fileUrl = "src/main/resources/sample_pdfs/sample_password_protected_bill.pdf";
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
