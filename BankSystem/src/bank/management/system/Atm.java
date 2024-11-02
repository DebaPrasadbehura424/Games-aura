package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Atm extends JFrame implements ActionListener {
    String pin;
    JLabel display;
    JTextField deposit;
    JButton depositButton, backButton, withdrawButton, balanceButton, pinChange;

    public Atm(String pin) {
        this.pin = pin;

        // Set background color
        getContentPane().setBackground(new Color(220, 220, 220));

        display = new JLabel("Enter deposit amount");
        display.setBounds(140, 20, 500, 20);
        display.setForeground(new Color(0, 102, 204)); // Set label color
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display);

        deposit = new JTextField();
        deposit.setBounds(100, 80, 300, 30);
        deposit.setFont(new Font("Arial", Font.PLAIN, 18));
        deposit.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(deposit);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(100, 150, 300, 40);
        depositButton.setFont(new Font("Arial", Font.BOLD, 18));
        depositButton.setBackground(new Color(0, 153, 0)); // Green color
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(this);
        add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 200, 300, 40);
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 18));
        withdrawButton.setBackground(new Color(204, 0, 0)); // Red color
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        balanceButton = new JButton("Balance");
        balanceButton.setBounds(100, 250, 300, 40);
        balanceButton.setFont(new Font("Arial", Font.BOLD, 18));
        balanceButton.setBackground(new Color(0, 102, 204)); // Blue color
        balanceButton.setForeground(Color.WHITE);
        balanceButton.addActionListener(this);
        add(balanceButton);

        pinChange = new JButton("PinChange");
        pinChange.setBounds(100, 300, 300, 40);
        pinChange.setFont(new Font("Arial", Font.BOLD, 18));
        pinChange.setBackground(new Color(255, 165, 0)); // Orange color
        pinChange.setForeground(Color.WHITE);
        pinChange.addActionListener(this);
        add(pinChange);

        backButton = new JButton("Back");
        backButton.setBounds(100, 350, 300, 40);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Atm("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            try {
                if (deposit.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter deposit amount");
                } else {
                    Con c1 = new Con();
                    String amount = deposit.getText();
                    Date date = new Date();
                    JOptionPane.showMessageDialog(null, amount + " deposited successfully");
                    String query = "INSERT INTO depositBar VALUES ('" + pin + "', '" + date + "', 'deposit', '" + amount + "')";
                    c1.statement.executeUpdate(query);
                    c1.connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Login();
        } else if (e.getSource() == balanceButton) {
            new Balance(pin);
            setVisible(false);
        } else if (e.getSource() == pinChange) {
            new PinChange(pin);
            setVisible(false);
        } else if (e.getSource() == withdrawButton) {
            new Withdraw(pin);
            setVisible(false);
        }
    }
}
