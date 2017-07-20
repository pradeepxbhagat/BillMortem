package com.billmortam.parser;

import com.billmortam.pdf.Pdf;

/**
 * Created by pp00344204 on 30/06/17.
 */
public interface Identifier {
    BillVendor identify(Pdf raw);
}
