package com.billmartam.parser;

import com.billmartam.pdf.Pdf;

/**
 * Created by pp00344204 on 30/06/17.
 */
public interface Identifier {
    BillVendor identify(Pdf raw);
}
