package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {

    JLabel app, accountTypeLabel, cardNumberLabel, servicesLabel, confirmationLabel;
    JRadioButton savingsButton, currentButton, fixedDepositButton, recurringDepositButton;
    JTextField cardNumberField;
    JCheckBox service1, service2, service3, service4;
    JCheckBox confirmationCheckbox;
    JButton submit, cancel;
    Random rand = new Random();
    long first4 = (rand.nextLong() % 9000L) + 1000L;
    String third = " " + Math.abs(first4);
    String cardNo;
    String pin;

    public Signup3(String cardNo, String pin) {
        this.cardNo = cardNo;
        this.pin = pin;

        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Light Blue background

        // Application Header
        app = new JLabel("Account Signup - Page" + third);
        app.setFont(new Font("Courier", Font.BOLD, 25));
        app.setBounds(220, 30, 300, 40);
        add(app);

        // Account Type
        accountTypeLabel = new JLabel("Account Type: ");
        accountTypeLabel.setFont(new Font("Courier", Font.BOLD, 20));
        accountTypeLabel.setBounds(20, 100, 300, 30);
        add(accountTypeLabel);

        savingsButton = new JRadioButton("Savings Account");
        currentButton = new JRadioButton("Current Account");
        fixedDepositButton = new JRadioButton("Fixed Deposit");
        recurringDepositButton = new JRadioButton("Recurring Deposit");

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(savingsButton);
        accountTypeGroup.add(currentButton);
        accountTypeGroup.add(fixedDepositButton);
        accountTypeGroup.add(recurringDepositButton);

        savingsButton.setBounds(200, 100, 200, 30);
        currentButton.setBounds(200, 130, 200, 30);
        fixedDepositButton.setBounds(200, 160, 200, 30);
        recurringDepositButton.setBounds(200, 190, 200, 30);

        add(savingsButton);
        add(currentButton);
        add(fixedDepositButton);
        add(recurringDepositButton);

        // Card Number
        cardNumberLabel = new JLabel("Card Number (16 digits): ");
        cardNumberLabel.setFont(new Font("Courier", Font.BOLD, 20));
        cardNumberLabel.setBounds(20, 230, 300, 30);
        add(cardNumberLabel);

        cardNumberField = new JTextField();
        cardNumberField.setBounds(250, 230, 200, 30);
        add(cardNumberField);

        // Services Required
        servicesLabel = new JLabel("Services Required: ");
        servicesLabel.setFont(new Font("Courier", Font.BOLD, 20));
        servicesLabel.setBounds(20, 270, 300, 30);
        add(servicesLabel);

        service1 = new JCheckBox("Online Banking");
        service2 = new JCheckBox("Mobile Banking");
        service3 = new JCheckBox("ATM Services");
        service4 = new JCheckBox("Check Book");

        service1.setBounds(250, 270, 200, 30);
        service2.setBounds(250, 300, 200, 30);
        service3.setBounds(250, 330, 200, 30);
        service4.setBounds(250, 360, 200, 30);

        add(service1);
        add(service2);
        add(service3);
        add(service4);

        // Confirmation Checkbox
        confirmationLabel = new JLabel("I confirm that all the above details are correct.");
        confirmationLabel.setFont(new Font("Courier", Font.BOLD, 16));
        confirmationLabel.setBounds(20, 400, 400, 30);
        add(confirmationLabel);

        confirmationCheckbox = new JCheckBox("Confirm");
        confirmationCheckbox.setBounds(450, 400, 100, 30);
        add(confirmationCheckbox);

        // Buttons
        submit = new JButton("Submit");
        submit.setFont(new Font("Courier", Font.BOLD, 20));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(300, 450, 100, 40);
        submit.setFocusable(false);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Courier", Font.BOLD, 20));
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(420, 450, 100, 40);
        cancel.setFocusable(false);
        cancel.addActionListener(e -> System.exit(0)); // Close the application
        add(cancel);

        setTitle("Signup 3");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String page3 = third;

        String accountType = null;
        if (savingsButton.isSelected()) accountType = "Savings Account";
        else if (currentButton.isSelected()) accountType = "Current Account";
        else if (fixedDepositButton.isSelected()) accountType = "Fixed Deposit";
        else if (recurringDepositButton.isSelected()) accountType = "Recurring Deposit";

        String cardNumber = cardNumberField.getText();
        String services = "";
        if (service1.isSelected()) services += "Online Banking ";
        if (service2.isSelected()) services += "Mobile Banking ";
        if (service3.isSelected()) services += "ATM Services ";
        if (service4.isSelected()) services += "Check Book ";

        boolean isConfirmed = confirmationCheckbox.isSelected();

        // Check if all required fields are filled
        try {
            if (accountType == null || cardNumber.isEmpty() || services.isEmpty() || !isConfirmed) {
                JOptionPane.showMessageDialog(null, "Please fill in all required details and confirm.");
            } else {
                Con con3 = new Con();
                String query3 = "insert into signupThree values ('" + page3 + "','" + accountType + "','" + cardNumber + "','" + services + "')";
                String loginData = "insert into loginup values ('" + page3 + "','" + cardNo + "','" + pin + "')";
                con3.statement.executeUpdate(query3);
                con3.statement.executeUpdate(loginData);

                setVisible(false);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Signup3("", "");
    }
}
