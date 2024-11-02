package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Signup2 extends JFrame implements ActionListener {

    JLabel religionLabel, categoryLabel, incomeLabel, panLabel, seniorCitizenLabel, occupationLabel, educationLabel, pageLabel;
    JTextField religionField, categoryField, incomeField, panField, occupationField, educationField;
    JRadioButton yesButton, noButton;
    JButton submit;

    Random rand = new Random();
    long first4 = (rand.nextLong() % 9000L) + 1000L;
    String second = " " + Math.abs(first4); // Random page number
    String cardNo;
    String pin;

    public Signup2(String cardNo, String pin) {
        this.cardNo = cardNo;
        this.pin = pin;

        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Light Blue

        // Page Number
        pageLabel = new JLabel("Page " + second);
        pageLabel.setFont(new Font("Courier", Font.BOLD, 25));
        pageLabel.setBounds(260, 30, 300, 40);
        add(pageLabel);

        // Religion
        religionLabel = new JLabel("Religion: ");
        religionLabel.setFont(new Font("Courier", Font.BOLD, 20));
        religionLabel.setBounds(20, 100, 300, 30);
        add(religionLabel);
        religionField = new JTextField();
        religionField.setBounds(200, 100, 250, 30);
        add(religionField);

        // Category
        categoryLabel = new JLabel("Category: ");
        categoryLabel.setFont(new Font("Courier", Font.BOLD, 20));
        categoryLabel.setBounds(20, 140, 300, 30);
        add(categoryLabel);
        categoryField = new JTextField();
        categoryField.setBounds(200, 140, 250, 30);
        add(categoryField);

        // Income
        incomeLabel = new JLabel("Income: ");
        incomeLabel.setFont(new Font("Courier", Font.BOLD, 20));
        incomeLabel.setBounds(20, 180, 300, 30);
        add(incomeLabel);
        incomeField = new JTextField();
        incomeField.setBounds(200, 180, 250, 30);
        add(incomeField);

        // PAN Number
        panLabel = new JLabel("PAN Number: ");
        panLabel.setFont(new Font("Courier", Font.BOLD, 20));
        panLabel.setBounds(20, 220, 300, 30);
        add(panLabel);
        panField = new JTextField();
        panField.setBounds(200, 220, 250, 30);
        add(panField);

        // Senior Citizen
        seniorCitizenLabel = new JLabel("Senior Citizen: ");
        seniorCitizenLabel.setFont(new Font("Courier", Font.BOLD, 20));
        seniorCitizenLabel.setBounds(20, 260, 300, 30);
        add(seniorCitizenLabel);
        yesButton = new JRadioButton("Yes");
        noButton = new JRadioButton("No");
        ButtonGroup seniorCitizenGroup = new ButtonGroup();
        seniorCitizenGroup.add(yesButton);
        seniorCitizenGroup.add(noButton);
        yesButton.setBounds(200, 260, 100, 30);
        noButton.setBounds(300, 260, 100, 30);
        add(yesButton);
        add(noButton);

        // Occupation
        occupationLabel = new JLabel("Occupation: ");
        occupationLabel.setFont(new Font("Courier", Font.BOLD, 20));
        occupationLabel.setBounds(20, 300, 300, 30);
        add(occupationLabel);
        occupationField = new JTextField();
        occupationField.setBounds(200, 300, 250, 30);
        add(occupationField);

        // Educational Qualification
        educationLabel = new JLabel("Educational Qualification: ");
        educationLabel.setFont(new Font("Courier", Font.BOLD, 20));
        educationLabel.setBounds(20, 340, 300, 30);
        add(educationLabel);
        educationField = new JTextField();
        educationField.setBounds(200, 340, 250, 30);
        add(educationField);

        // Submit Button
        submit = new JButton("Next");
        submit.setFont(new Font("Courier", Font.BOLD, 20));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(470, 400, 100, 40);
        submit.setFocusable(false);
        submit.addActionListener(this);
        add(submit);

        setTitle("Signup 2");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Signup2("", "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String page2 = second;
        String religion = religionField.getText();
        String category = categoryField.getText();
        String income = incomeField.getText();
        String pan = panField.getText();
        String occupation = occupationField.getText();
        String education = educationField.getText();
        String seniorCitizen = yesButton.isSelected() ? "Yes" : noButton.isSelected() ? "No" : null;

        try {
            if (religion.isEmpty() || category.isEmpty() || income.isEmpty() || pan.isEmpty() || occupation.isEmpty() || education.isEmpty() || seniorCitizen == null) {
                JOptionPane.showMessageDialog(null, "Fill all the data first");
            } else {
                Con con1 = new Con();
                String query2 = "insert into signupTwo values ('" + page2 + "','" + religion + "','" + category + "','" + income + "','" + pan + "','" + occupation + "','" + education + "','" + seniorCitizen + "')";
                con1.statement.executeUpdate(query2);
                new Signup3(cardNo, pin);
                setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
        }
    }
}
