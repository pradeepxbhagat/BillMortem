package billmortam.parser;


import billmortam.pdf.Pdf;
import billmortam.util.TextSearch;

/**
 * Created by pp00344204 on 30/06/17.
 */
public class BillIdentifier implements Identifier{
    private static final String CITI_CC_IDENTIFIER = "CITIBANK";
    private static final String ICICI_CC_IDENTIFIER = "ICICI";
    private static BillIdentifier identifier;

    public static Identifier getIdentifier() {
        if(identifier == null){
            identifier = new BillIdentifier();
        }
        return identifier;
    }

    @Override
    public BillVendor identify(Pdf data) {
        return parseForIdentifier(data);
    }

    private BillVendor parseForIdentifier(Pdf data) {
        if(isCtitBill(data)){
            return BillVendor.CITI;
        }else if(isIciciBill(data)){
            return BillVendor.ICICI;
        }
        return BillVendor.HDFC;
    }

    private boolean isIciciBill(Pdf data) {
        return TextSearch.match(ICICI_CC_IDENTIFIER,data.getData());
    }

    private boolean isCtitBill(Pdf data) {
        /*if(isFirstWord(data.getData()).equals(CITI_CC_IDENTIFIER)){
            return true;
        }*/
        return TextSearch.search(CITI_CC_IDENTIFIER,data.getData());
    }

}
