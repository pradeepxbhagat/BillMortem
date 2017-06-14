package pdf;

import org.junit.Test;

/**
 * Created by pp00344204 on 07/06/17.
 */
public class PdfChooserViewTest {

    @Test
    public void open_pdf_chooser_view_test(){
        PdfChooserView chooser = new PdfChooserView();
        chooser.init();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
