package pdf;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by pp00344204 on 08/06/17.
 */
class PdfDisplayView extends JPanel{

    private final JFrame container;
    private JScrollPane pdfScroll;

    private JTextArea pdfDisplayArea;
    private JPanel middlePanel;

    public PdfDisplayView(JFrame container) {
        this.container = container;
    }

    public void displayPdf() {
        /*JTextArea area = getPdfDisplayArea(pdfData);

        JScrollPane scroll = getPdfScroll(area);

        JPanel middlePanel = getMiddlePanel(scroll);
        middlePanel.validate();
        container.validate();*/

        JPanel searchPanel = getSearchPanel();
        putSearchPanelInContainer(container,searchPanel);
    }

    private void putSearchPanelInContainer(JFrame container, JPanel searchPanel) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridwidth = 1;
        constraints.gridheight=1;

        container.getContentPane().add(searchPanel,constraints);
        container.validate();
    }

    private JScrollPane getPdfScroll(JTextArea area) {
        if (pdfScroll == null) {
            pdfScroll = new JScrollPane();
//            pdfScroll.setBounds(10,60,780,500);
//            pdfScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }
        pdfScroll.setViewportView(area);
        return pdfScroll;
    }

    private JTextArea getPdfDisplayArea(String pdfData) {
        if (pdfDisplayArea == null) {
            pdfDisplayArea = new JTextArea();
            pdfDisplayArea.setEditable(false);
            pdfDisplayArea.setRows(40);
            pdfDisplayArea.setColumns(100);
        }
        pdfDisplayArea.setText(pdfData);
        return pdfDisplayArea;
    }

    private JPanel getMiddlePanel(JScrollPane scroll) {
        if (middlePanel == null) {

            middlePanel = new JPanel();
            middlePanel.setSize(JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
            middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Pdf content: "));
            middlePanel.setLayout(new FlowLayout());
            middlePanel.add(scroll);
            container.getContentPane().add(middlePanel);
            container.validate();
        }

        return middlePanel;
    }

    private JPanel getSearchPanel() {
        JTextField searchField = new JTextField(30);
        JLabel label = new JLabel("Search: ");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weighty = 1.0;
        constraints.weightx = 1.0;
        jPanel.add(label, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        jPanel.add(searchField, constraints);

        return jPanel;
    }


}
