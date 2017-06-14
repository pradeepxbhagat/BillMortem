package report;

import expenditure.ExpenditureCalculator;
import parser.HdfcBillParser;
import transaction.TransactionSearch;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class ReportGenerator {

    public static ReportGenerator getGenerator() {
        return new ReportGenerator();
    }

    public TransactionReport genrateFrom(String raw) {
        HdfcBillParser hdfcBillParser = new HdfcBillParser();
        return hdfcBillParser.parse(raw);
    }

    public TransactionReport generateFor(String searchTerm, TransactionReport from) {
        TransactionSearch transactionSearch = TransactionSearch.getSearchEngine(from);
        return transactionSearch.searchTransaction(searchTerm);
    }

    public double getTotalExpenditure(TransactionReport searchedTransactions) {
        ExpenditureCalculator expenditureCalculator = ExpenditureCalculator.getCalculator();
        return expenditureCalculator.calculateTotalExpenditure(searchedTransactions);
    }
}
