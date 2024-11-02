package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel labelData, cardNo, pin;
    JTextField cardText;
    JPasswordField pinText;
    JButton signUp, signIn, clear;

    Login() {
        setTitle("Bank Management System");

        // Text
        labelData = new JLabel("LOGIN FORM");
        labelData.setFont(new Font("Courier", Font.BOLD, 25));
        labelData.setForeground(Color.BLACK);
        labelData.setBounds(350, 30, 450, 40);
        add(labelData);




        cardNo = new JLabel("CARD NO:");
        cardNo.setFont(new Font("Courier", Font.BOLD, 20));
        cardNo.setForeground(Color.BLACK);
        cardNo.setBounds(150, 100, 450, 40);
        add(cardNo);

        pin = new JLabel("PIN:");
        pin.setFont(new Font("Courier", Font.BOLD, 20));
        pin.setForeground(Color.BLACK);
        pin.setBounds(206, 160, 450, 40);
        add(pin);

        // Fields
        cardText = new JTextField();
        cardText.setBounds(290, 110, 350, 30);
        cardText.setFont(new Font("Arial", Font.BOLD, 20));
        add(cardText);

        pinText = new JPasswordField(15);
        pinText.setBounds(290, 165, 350, 30);
        add(pinText);

        // Clear button
        clear = new JButton("CLEAR");
        clear.setFont(new Font("Courier", Font.BOLD, 20));
        clear.setForeground(Color.WHITE);
        clear.setBackground(Color.BLACK);
        clear.setBounds(490, 260, 150, 30);
        clear.setFocusable(false);
        clear.addActionListener(this);
        add(clear);

        // Sign in button
        signIn = new JButton("SIGN IN");
        signIn.setFont(new Font("Courier", Font.BOLD, 20));
        signIn.setForeground(Color.WHITE);
        signIn.setBackground(Color.BLACK);
        signIn.setBounds(290, 260, 150, 30);
        signIn.setFocusable(false);
        signIn.addActionListener(this);
        add(signIn);

        // Sign up button
        signUp = new JButton("SIGN UP");
        signUp.setFont(new Font("Courier", Font.BOLD, 20));
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.BLACK);
        signUp.setBounds(290, 350, 350, 40);
        signUp.setFocusable(false);
        signUp.addActionListener(this);
        add(signUp);

        setLayout(null);
        setSize(850, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == clear) {
                cardText.setText("");
                pinText.setText("");
            } else if (e.getSource() == signIn) {
                Con n = new Con(); // Ensure this connects to your database
                String cardNo = cardText.getText();
                String pin = new String(pinText.getPassword()); // Get the password correctly
                String q = "SELECT * FROM loginup WHERE cardNo='" + cardNo + "' AND pin='" + pin + "'";
                ResultSet resultSet = n.statement.executeQuery(q);
                if (resultSet.next()) {
                    setVisible(false);
                    new Atm(pin);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Card No or PIN");
                }
            } else if (e.getSource() == signUp) {
                String cardNo = cardText.getText();
                String pin = new String(pinText.getPassword()); // Get the password correctly
                setVisible(false);
                new Signup(cardNo, pin); // Pass parameters to Signup
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred. Please try again.");
        }
    }
}
