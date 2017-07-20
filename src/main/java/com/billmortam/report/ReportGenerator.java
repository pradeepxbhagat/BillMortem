package com.billmortam.report;

import com.billmortam.expenditure.ExpenditureCalculator;
import com.billmortam.parser.HdfcBillParser;
import com.billmortam.parser.Parser;
import com.billmortam.pdf.Pdf;
import com.billmortam.transaction.TransactionSearch;

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
