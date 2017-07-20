package com.billmortam.parser;

import com.billmortam.pdf.Pdf;
import com.billmortam.report.TransactionReport;

/**
 * Created by pp00344204 on 06/06/17.
 */
public interface Parser  {
    TransactionReport parse(Pdf raw, boolean canUseParserCache);
}
