package com.billmartam.parser;

import com.billmartam.report.TransactionReport;

/**
 * Created by pp00344204 on 06/06/17.
 */
public interface Parser {
    TransactionReport parse(String raw);
}
