package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PinChange extends JFrame implements ActionListener {
    JLabel changeSign, pinSign, heading;
    JTextField ChangeBox, cardBox;
    JButton changeButton, BackButton;
    String pin;

    public PinChange(String pin) {
        this.pin = pin;

        // Styling heading label
        heading = new JLabel("Enter your name and new pin");
        heading.setFont(new Font("Courier", Font.BOLD, 20));
        heading.setForeground(Color.BLACK);
        heading.setBounds(150, 20, 300, 30);
        add(heading);

        // Styling card number label
        changeSign = new JLabel("CARD NO:");
        changeSign.setFont(new Font("Courier", Font.BOLD, 20));
        changeSign.setForeground(Color.BLACK);
        changeSign.setBounds(50, 100, 450, 40);
        add(changeSign);

        // Styling pin label
        pinSign = new JLabel("PIN:");
        pinSign.setFont(new Font("Courier", Font.BOLD, 20));
        pinSign.setForeground(Color.BLACK);
        pinSign.setBounds(106, 160, 450, 40);
        add(pinSign);

        // Styling text fields
        cardBox = new JTextField();
        cardBox.setBounds(200, 110, 250, 30);
        cardBox.setFont(new Font("Courier", Font.PLAIN, 16));
        cardBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        add(cardBox);

        ChangeBox = new JTextField();
        ChangeBox.setBounds(200, 165, 250, 30);
        ChangeBox.setFont(new Font("Courier", Font.PLAIN, 16));
        ChangeBox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        add(ChangeBox);

        // Styling change button
        changeButton = new JButton("CHANGE");
        changeButton.setFont(new Font("Courier", Font.BOLD, 20));
        changeButton.setForeground(Color.WHITE);
        changeButton.setBackground(Color.GREEN);
        changeButton.setBounds(200, 200, 250, 30);
        changeButton.addActionListener(this);
        add(changeButton);

        // Styling back button
        BackButton = new JButton("BACK");
        BackButton.setFont(new Font("Courier", Font.BOLD, 20));
        BackButton.setForeground(Color.WHITE);
        BackButton.setBackground(Color.RED);
        BackButton.setBounds(200, 300, 250, 30);
        BackButton.addActionListener(this);
        add(BackButton);

        // Frame settings
        setTitle("Pin Change");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY); // Background color for the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new PinChange("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cardNo = cardBox.getText();
        String pinNo = ChangeBox.getText();
        if (e.getSource() == changeButton) {
            Con c1 = new Con();
            try {
                if (cardNo.equals("") || pinNo.equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all required data");
                } else {
                    String query = "UPDATE loginup SET pin='" + pinNo + "' WHERE cardNo='" + cardNo + "' AND pin = '" + pin + "'";
                    String query2 = "UPDATE depositBar SET pin='" + pinNo + "' WHERE pin='" + pin + "'";
                    c1.statement.executeUpdate(query);
                    c1.statement.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Pin changed successfully.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == BackButton) {
            new Atm(pin);
            setVisible(false);
        }
    }
}
