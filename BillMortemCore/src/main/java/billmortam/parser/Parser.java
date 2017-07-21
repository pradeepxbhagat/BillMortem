package billmortam.parser;


import billmortam.pdf.Pdf;
import billmortam.report.TransactionReport;

/**
 * Created by pp00344204 on 06/06/17.
 */
public interface Parser  {
    TransactionReport parse(Pdf raw, boolean canUseParserCache);
}
