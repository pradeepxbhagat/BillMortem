package pdf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PdfPasswordGraber extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private PasswordListener listener;
    private JLabel lblMessage;
    private JPasswordField lblPassword;

    public PdfPasswordGraber() {
        this(null,null);
    }

    public PdfPasswordGraber(PasswordListener listener) {
        this(listener, null);
    }

    public PdfPasswordGraber(PasswordListener listener, String message) {
        this.listener = listener;
        if(message  != null) {
            lblMessage.setText(message);
        }
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2 - 200, dim.height/2-this.getSize().height/2  -200);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if(listener != null) {
            listener.grabPassword(lblPassword.getText());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    public static void main(String[] args) {
        PdfPasswordGraber dialog = new PdfPasswordGraber();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public interface PasswordListener {
        void grabPassword(String password);
        void onDialogCancel();
    }
}
