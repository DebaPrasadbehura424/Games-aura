package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Balance extends JFrame implements ActionListener {
    JLabel total;
    static String pin;
    JButton backAtm;

    public Balance(String pin) {
        this.pin = pin;

        Con c1 = new Con();
        int totalDeposit = 0;
        int totalWithdrawal = 0;

        try {
            // Fetch total deposit
            ResultSet depositResult = c1.statement.executeQuery("SELECT SUM(amount) FROM depositBar WHERE pin='" + pin + "' AND type='deposit'");
            if (depositResult.next()) {
                totalDeposit = depositResult.getInt(1);
            }

            // Fetch total withdrawal
            ResultSet withdrawalResult = c1.statement.executeQuery("SELECT SUM(amount) FROM depositBar WHERE pin='" + pin + "' AND type='withdrawal'");
            if (withdrawalResult.next()) {
                totalWithdrawal = withdrawalResult.getInt(1);
            }

            // Calculate the balance
            int balance = totalDeposit - totalWithdrawal;

            // Display balance
            total = new JLabel("Total Balance: " + Math.max(balance, 0));
            total.setFont(new Font("Arial", Font.BOLD, 20));
            total.setForeground(Color.BLACK);
            total.setBounds(170, 30, 300, 100);
            add(total);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Styling back button
        backAtm = new JButton("BACK");
        backAtm.setFont(new Font("Courier", Font.BOLD, 20));
        backAtm.setForeground(Color.WHITE);
        backAtm.setBackground(Color.RED);
        backAtm.setBounds(130, 300, 250, 30);
        backAtm.addActionListener(this);
        add(backAtm);

        // Frame settings
        setTitle("Balance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);

        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.LIGHT_GRAY); // Background color for the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new Balance("1234"); // Pass the appropriate PIN here for testing
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backAtm) {
            setVisible(false);
            new Atm(pin);
        }
    }
}
