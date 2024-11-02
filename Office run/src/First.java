import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class First extends JFrame implements ActionListener {
    JLabel Name, password;
    JTextField username, userpass;
    JButton have, none,leaderboard;

    public First() {
        BackgroundPanel backgroundPanel = new BackgroundPanel("./objects/bgrun.jpg");
        backgroundPanel.setLayout(null);

        // Initialize components
        Name = new JLabel("Name: ");
        Name.setFont(new Font("Arial", Font.BOLD, 22));
        Name.setBounds(20, 90, 100, 30);
        Name.setForeground(new Color(0, 0, 0));
        backgroundPanel.add(Name);

        password = new JLabel("Password:");
        password.setFont(new Font("Arial", Font.BOLD, 22));
        password.setBounds(20, 135, 150, 30);
        password.setForeground(new Color(0, 0, 0)); // Soft light color
        backgroundPanel.add(password);

        username = new JTextField();
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setBounds(200, 90, 200, 30);
        username.setBackground(new Color(240, 240, 240)); // Light gray
        username.setBorder(BorderFactory.createLineBorder(new Color(184, 134, 11), 2)); // Gold border
        backgroundPanel.add(username);

        userpass = new JTextField();
        userpass.setFont(new Font("Arial", Font.PLAIN, 20));
        userpass.setBounds(200, 130, 200, 30);
        userpass.setBackground(new Color(240, 240, 240)); // Light gray
        userpass.setBorder(BorderFactory.createLineBorder(new Color(184, 134, 11), 2)); // Gold border
        backgroundPanel.add(userpass);

        have = new JButton("Already have");
        have.setFont(new Font("Arial", Font.BOLD, 20));
        have.setBounds(200, 180, 200, 40);
        have.setFocusable(false);
        have.setBackground(new Color(0, 255, 223)); // Forest green
        have.setForeground(Color.WHITE); // White text
        have.setBorder(BorderFactory.createEmptyBorder());
        have.addActionListener(this);
        backgroundPanel.add(have);

        none = new JButton("Submit");
        none.setFont(new Font("Arial", Font.BOLD, 20));
        none.setBounds(200, 230, 200, 40);
        none.setFocusable(false);
        none.setBackground(new Color(0, 255, 255)); // Forest green
        none.setForeground(Color.WHITE); // White text
        none.setBorder(BorderFactory.createEmptyBorder());
        none.addActionListener(this);
        backgroundPanel.add(none);

        leaderboard = new JButton("Leaderboard");
        leaderboard.setFont(new Font("Arial", Font.BOLD, 20));
        leaderboard.setBounds(200, 290, 200, 40);
        leaderboard.setFocusable(false);
        leaderboard.setBackground(new Color(0, 255, 255)); // Forest green
        leaderboard.setForeground(Color.WHITE); // White text
        leaderboard.setBorder(BorderFactory.createEmptyBorder());
        leaderboard.addActionListener(this);
        backgroundPanel.add(leaderboard);

        // Set up the frame
        setTitle("First");
        setResizable(false);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new First();
    }

    public void actionPerformed(ActionEvent e) {
        String names = username.getText();
        String passwords = userpass.getText();
        String score = "0";

        if (e.getSource() == have) {
            Link li = new Link();
            try {
                String n = "SELECT * FROM officebar WHERE name='" + names + "' AND pass='" + passwords + "'";
                ResultSet resultSet = li.statement.executeQuery(n);

                if (resultSet.next()) {
                    openOfficeFrame(names);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "You have no account");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                li.close();
            }
        } else if (e.getSource() == none) {
            Link li = new Link();
            try {
                String q = "INSERT INTO officebar (score, name, pass) VALUES('0', '" + names + "', '" + passwords + "')";
                li.statement.executeUpdate(q);
                openOfficeFrame(names);
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                li.close();
            }
        } else if (e.getSource()==leaderboard) {
            Leader l = new Leader();
             l.updateLeaderBoard();
            this.dispose();

        }
    }

    private void openOfficeFrame(String username) {
        JFrame officeFrame = new JFrame("Office");
        officeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        officeFrame.add(new Office(username));
        officeFrame.pack();
        officeFrame.setLocationRelativeTo(null);
        officeFrame.setVisible(true);
    }
}
