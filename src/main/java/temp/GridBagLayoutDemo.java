package temp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pp00344204 on 09/06/17.
 */
public class GridBagLayoutDemo extends JFrame {
    public static void main(String[] args) {
        GridBagLayoutDemo a = new GridBagLayoutDemo();
    }

    public GridBagLayoutDemo() {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(grid);

        setTitle("GridBag Layout Example");
        /*GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);*/
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(new Button("Button One"), gbc);

        JLabel label = new JLabel("Search: ");
        JTextField textField = new JTextField(30);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(textField);

        gbc.fill = GridBagConstraints.LINE_START | GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        this.add(panel, gbc);

        /*gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;

        this.add(new Button("Button Three"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(new Button("Button Four"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        this.add(new Button("Button Five"), gbc);*/

        setSize(300, 300);
        setPreferredSize(getSize());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
