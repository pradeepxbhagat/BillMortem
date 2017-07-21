package billmortam.report;


import billmortam.expenditure.ExpenditureCalculator;
import billmortam.parser.HdfcBillParser;
import billmortam.parser.Parser;
import billmortam.pdf.Pdf;
import billmortam.transaction.TransactionSearch;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class ReportGenerator {

    public static ReportGenerator getGenerator() {
        return new ReportGenerator();
    }

    public TransactionReport genrateFrom(Pdf raw) {
        if (raw == null) {
            return null;
        }
        Parser billParser = new HdfcBillParser();
        return billParser.parse(raw, false);
    }

    public TransactionReport generateFor(String searchTerm, TransactionReport from) {
        if (from == null) {
            return null;
        }
        TransactionSearch transactionSearch = TransactionSearch.getSearchEngine(from);
        return transactionSearch.searchTransaction(searchTerm);
    }

    public double getTotalExpenditure(TransactionReport searchedTransactions) {
        if (searchedTransactions == null) return -1;
        ExpenditureCalculator expenditureCalculator = ExpenditureCalculator.getCalculator();
        return expenditureCalculator.calculateTotalExpenditure(searchedTransactions);
    }
}
