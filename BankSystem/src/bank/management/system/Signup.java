package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {

    JLabel app, nameLabel, emailLabel, maritalLabel, genderLabel, addressLabel, cityLabel, stateLabel, pinCodeLabel;
    JTextField nameField, emailField, addressField, cityField, stateField, pinCodeField;
    JRadioButton marriedButton, singleButton, otherButton, maleButton, femaleButton, otherGenderButton;
    JButton next;

    Random rand = new Random();
    long first4 = (rand.nextLong() % 9000L) + 1000L;
    String first = " " + Math.abs(first4);

    String cardNo;
    String pin;

    public Signup(String cardNo, String pin) {
        this.cardNo = cardNo;
        this.pin = pin;

        app = new JLabel("BANK FORM" + first);
        app.setFont(new Font("Courier", Font.BOLD, 25));
        app.setForeground(Color.BLACK);
        app.setBounds(260, 30, 300, 40);
        add(app);
        getContentPane().setBackground(new Color(245, 222, 179)); // Beige

        // Name
        nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Courier", Font.BOLD, 20));
        nameLabel.setBounds(20, 100, 300, 30);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(200, 100, 250, 30);
        add(nameField);

        // Email
        emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Courier", Font.BOLD, 20));
        emailLabel.setBounds(20, 140, 300, 30);
        add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(200, 140, 250, 30);
        add(emailField);

        // Marital Status
        maritalLabel = new JLabel("Marital Status: ");
        maritalLabel.setFont(new Font("Courier", Font.BOLD, 20));
        maritalLabel.setBounds(20, 220, 300, 30);
        add(maritalLabel);
        marriedButton = new JRadioButton("Married");
        singleButton = new JRadioButton("Single");
        otherButton = new JRadioButton("Others");
        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(marriedButton);
        maritalGroup.add(singleButton);
        maritalGroup.add(otherButton);
        marriedButton.setBounds(200, 220, 100, 30);
        singleButton.setBounds(300, 220, 100, 30);
        otherButton.setBounds(400, 220, 100, 30);
        add(marriedButton);
        add(singleButton);
        add(otherButton);

        // Gender
        genderLabel = new JLabel("Gender: ");
        genderLabel.setFont(new Font("Courier", Font.BOLD, 20));
        genderLabel.setBounds(20, 260, 300, 30);
        add(genderLabel);
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        otherGenderButton = new JRadioButton("Others");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherGenderButton);
        maleButton.setBounds(200, 260, 100, 30);
        femaleButton.setBounds(300, 260, 100, 30);
        otherGenderButton.setBounds(400, 260, 100, 30);
        add(maleButton);
        add(femaleButton);
        add(otherGenderButton);

        // Address
        addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Courier", Font.BOLD, 20));
        addressLabel.setBounds(20, 300, 300, 30);
        add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(200, 300, 250, 30);
        add(addressField);

        // City
        cityLabel = new JLabel("City: ");
        cityLabel.setFont(new Font("Courier", Font.BOLD, 20));
        cityLabel.setBounds(20, 340, 300, 30);
        add(cityLabel);
        cityField = new JTextField();
        cityField.setBounds(200, 340, 250, 30);
        add(cityField);

        // State
        stateLabel = new JLabel("State: ");
        stateLabel.setFont(new Font("Courier", Font.BOLD, 20));
        stateLabel.setBounds(20, 380, 300, 30);
        add(stateLabel);
        stateField = new JTextField();
        stateField.setBounds(200, 380, 250, 30);
        add(stateField);

        // Pin Code
        pinCodeLabel = new JLabel("Pin Code: ");
        pinCodeLabel.setFont(new Font("Courier", Font.BOLD, 20));
        pinCodeLabel.setBounds(20, 420, 300, 30);
        add(pinCodeLabel);
        pinCodeField = new JTextField();
        pinCodeField.setBounds(200, 420, 250, 30);
        add(pinCodeField);

        // Next Button
        next = new JButton("Next");
        next.setFont(new Font("Courier", Font.BOLD, 20));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(470, 470, 100, 40);
        next.setFocusable(false);
        next.addActionListener(this);
        add(next);

        setTitle("Signup");
        setLayout(null);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Signup("", "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formNo = first;
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String pinCode = pinCodeField.getText();
        String gender = null;

        if (maleButton.isSelected()) {
            gender = "male";
        } else if (femaleButton.isSelected()) {
            gender = "female";
        } else if (otherGenderButton.isSelected()) {
            gender = "others";
        }

        String marital = null;
        if (marriedButton.isSelected()) {
            marital = "married";
        } else if (singleButton.isSelected()) {
            marital = "single";
        } else if (otherButton.isSelected()) {
            marital = "others";
        }

        try {
            if (name.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || pinCode.isEmpty() || gender == null || marital == null) {
                JOptionPane.showMessageDialog(null, "Fill all the data first");
            } else {
                Con con1 = new Con();
                String query = "insert into signup values ('" + formNo + "','" + name + "','" + email + "','" + gender + "','" + city + "','" + marital + "','" + state + "','" + address + "','" + pinCode + "')";
                con1.statement.executeUpdate(query);
                new Signup2(cardNo, pin);
                setVisible(false);
            }
        } catch (Exception a) {
            a.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + a.getMessage());
        }
    }
}
