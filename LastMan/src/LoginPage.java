import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class LoginPage extends JFrame implements ActionListener {
    ImageIcon back;
    ImageIcon icon;

    JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton createButton, alreadyExistsButton, leaveButton;

    public LoginPage() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(530, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set background image
        back = new ImageIcon(ClassLoader.getSystemResource("images/HomeBack.jpg"));
        icon = new ImageIcon(ClassLoader.getSystemResource("images/Back.jpg"));
        setIconImage(icon.getImage());


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null);
        add(panel);

        usernameLabel = new JLabel("GamerName");
        usernameLabel.setBounds(50, 150, 200, 30);
        usernameLabel.setForeground(Color.LIGHT_GRAY);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(usernameLabel);

        passwordLabel = new JLabel("SecretKey");
        passwordLabel.setBounds(50, 200, 200, 30);
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(220, 150, 200, 30);
        usernameField.setForeground(Color.WHITE);
        usernameField.setBackground(new Color(30,30,30));
        usernameField.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(220, 200, 200, 30);
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(new Color(30,30,30));
        passwordField.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(passwordField);

        createButton = new JButton("Create Account");
        createButton.setBounds(50, 250, 200, 40);
        createButton.setFont(new Font("Arial", Font.BOLD, 16));
        createButton.setBackground(new Color(30, 30, 30));
        createButton.setForeground(Color.WHITE);
        createButton.setFocusable(false);
        createButton.addActionListener(this);
        panel.add(createButton);

        alreadyExistsButton = new JButton("Already Exists");
        alreadyExistsButton.setBounds(280, 250, 200, 40);
        alreadyExistsButton.setFont(new Font("Arial", Font.BOLD, 16));
        alreadyExistsButton.setBackground(new Color(30, 30, 30));
        alreadyExistsButton.setForeground(Color.WHITE);
        alreadyExistsButton.setFocusable(false);
        alreadyExistsButton.addActionListener(this);
        panel.add(alreadyExistsButton);

        leaveButton = new JButton("Leave");
        leaveButton.setBounds(170, 320, 200, 40);
        leaveButton.setFont(new Font("Arial", Font.BOLD, 16));
        leaveButton.setBackground(new Color(30, 30, 30));
        leaveButton.setForeground(Color.WHITE);
        leaveButton.setFocusable(false);
        leaveButton.addActionListener(this);
        panel.add(leaveButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String Password=passwordField.getText();

        if(e.getSource()==createButton){
            String firstCharacter="images/Jack.gif";
            String FirstCoins="images/Coinc.png";
            String FirstEnviro="images/jungle.png";
            String FirstRoad="images/GrassRoad.png";

            if(username.isEmpty() || Password.isEmpty()){
                JOptionPane.showMessageDialog(null,"Your data is not sufficient ");
            }else{
                try{
                    Config database = new Config();
                    String query = "INSERT INTO ManInfo (username, userpass) VALUES ('" + username + "', '" + Password + "')";
                    database.statement.executeUpdate(query);
                    String query2="INSERT INTO Elements VALUES( '"+username+"','"+Password+"','"+firstCharacter+"','"+FirstCoins+"','"+FirstRoad+"','"+FirstEnviro+"')";
                    database.statement.executeUpdate(query2);


                    new StartHome(username,Password);
                    setVisible(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Give stronger password already someone use it ");
                }

            }


        }else if(e.getSource()==alreadyExistsButton){
            if(username.isEmpty() || Password.isEmpty()){
                JOptionPane.showMessageDialog(null,"Your data is not sufficient");
            }else{
                try {
                    Config database = new Config();
                    String query = "SELECT * FROM ManInfo WHERE username = '" + username + "' AND userpass = '" + Password + "'";
                    ResultSet result = database.statement.executeQuery(query);
                    if(result.next()){
                        new StartHome(username,Password);
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Not found ");
                    }
                }catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        } else {
         System.exit(0);
        }
    }
}
