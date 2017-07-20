import com.billmortam.pdf.Pdf;
import com.billmortam.pdf.PdfReaderView;

import javax.swing.*;

/**
 * Created by pp00344204 on 07/06/17.
 */
class Test {

    public static void main(String[] args){
        PdfReaderView readerView = new PdfReaderView(new JFrame(), new Pdf());
    }
}
