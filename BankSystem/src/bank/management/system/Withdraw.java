package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    JLabel display;
    JTextField withdrawput;
    JButton withdraw, BackButton;
    String pin;

    public Withdraw(String pin) {
        this.pin = pin;

        // Styling display label
        display = new JLabel("Enter withdrawal amount");
        display.setBounds(140, 20, 500, 20);
        display.setForeground(Color.black);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        add(display);

        // Styling text field
        withdrawput = new JTextField();
        withdrawput.setBounds(100, 80, 300, 30);
        withdrawput.setFont(new Font("Arial", Font.PLAIN, 16));
        withdrawput.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // Adding border
        add(withdrawput);

        // Styling withdraw button
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(100, 150, 300, 30);
        withdraw.setBackground(Color.GREEN);
        withdraw.setForeground(Color.WHITE);
        withdraw.setFont(new Font("Arial", Font.BOLD, 16));
        withdraw.addActionListener(this);
        add(withdraw);

        // Styling back button
        BackButton = new JButton("Back");
        BackButton.setBounds(100, 200, 300, 30);
        BackButton.setBackground(Color.RED);
        BackButton.setForeground(Color.WHITE);
        BackButton.setFont(new Font("Arial", Font.BOLD, 16));
        BackButton.addActionListener(this);
        add(BackButton);

        // Frame settings
        setTitle("Withdraw");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY); // Background color for the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new Withdraw("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdraw) {
            String amount = withdrawput.getText();
            if (amount.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter amount to withdraw");
            } else {
                try {
                    Con c1 = new Con();
                    Date date = new Date();
                    String query = "INSERT INTO depositBar VALUES ('" + pin + "', '" + date + "', 'withdrawal', '" + amount + "')";
                    c1.statement.executeUpdate(query);
                    int totalDeposit = 0;
                    int totalWithdrawal = 0;

                    ResultSet depositResult = c1.statement.executeQuery("SELECT SUM(amount) FROM depositBar WHERE pin='" + pin + "' AND type='deposit'");
                    if (depositResult.next()) {
                        totalDeposit = depositResult.getInt(1);
                    }

                    ResultSet withdrawalResult = c1.statement.executeQuery("SELECT SUM(amount) FROM depositBar WHERE pin='" + pin + "' AND type='withdrawal'");
                    if (withdrawalResult.next()) {
                        totalWithdrawal = withdrawalResult.getInt(1);
                    }
                    int balance = totalDeposit - totalWithdrawal;
                    if (balance < 0) {
                        JOptionPane.showMessageDialog(null, "You don't have enough balance");
//                        c1.statement.executeUpdate("DELETE FROM depositBar WHERE pin='" + pin + "' AND type='withdrawal' ORDER BY date DESC LIMIT 1");
                        c1.statement.executeUpdate("TRUNCATE TABLE depositBar ");
                    } else {
                        JOptionPane.showMessageDialog(null, "Withdraw Successful");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } else if (e.getSource() == BackButton) {
            setVisible(false);
            new Atm(pin);
        }
    }
}
