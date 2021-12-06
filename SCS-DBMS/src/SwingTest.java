import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class SwingTest {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public SwingTest() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingTest swingTest = new SwingTest();
        swingTest.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("SCS DBMS");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        headerLabel.setText("Welcome to the Towson University SCS DBMS!");

        JLabel tuidLabel = new JLabel("TUID:");
        JFormattedTextField tuidField = new JFormattedTextField(createFormatter("#######"));
        JLabel pswdLabel = new JLabel("Password:");
        JPasswordField pswdField = new JPasswordField(10);
        JButton submitButton = new JButton("Submit");

        tuidField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText(e.toString());
            }
        });

        submitButton.setActionCommand("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Username: " + tuidField.getText() + " Password: " + new String(pswdField.getPassword()));
            }
        });

        controlPanel.add(tuidLabel);
        controlPanel.add(tuidField);
        controlPanel.add(pswdLabel);
        controlPanel.add(pswdField);
        controlPanel.add(submitButton);

        mainFrame.setVisible(true);
    }

    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        }   catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
}