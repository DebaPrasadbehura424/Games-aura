import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnviromtnFile extends JFrame {
    ImageIcon JungleIcon, RadiationIcon, BeachIcon, CityIcon;
String username;
String Password;

    public EnviromtnFile(String username, String Password) {
        this.username = username;
        this.Password = Password;

        setTitle("Environment Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(30, 30, 30));

        JungleIcon = new ImageIcon(ClassLoader.getSystemResource("images/Jungle.png"));
        RadiationIcon = new ImageIcon(ClassLoader.getSystemResource("images/Radium.jpg"));
        BeachIcon = new ImageIcon(ClassLoader.getSystemResource("images/Beach.png"));
        CityIcon = new ImageIcon(ClassLoader.getSystemResource("images/City.png"));

        setLayout(null);

        JLabel titleLabel = new JLabel("Environment Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setBounds(200, 10, 200, 30);
        add(titleLabel);

        addCharacterPanel(JungleIcon, 20, 50, "Jungle", "images/Jungle.png");
        addCharacterPanel(RadiationIcon, 170, 50, "Radiation", "images/Radium.jpg");
        addCharacterPanel(BeachIcon, 320, 50, "Beach", "images/Beach.png");
        addCharacterPanel(CityIcon, 470, 50, "City", "images/City.png");

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(250, 500, 100, 30);
        BackButton.setBackground(new Color(100, 149, 237));
        BackButton.setForeground(Color.BLACK);
        BackButton.setFont(new Font("Arial", Font.BOLD, 12));
        BackButton.setFocusable(false);
        BackButton.setBorderPainted(false);
        BackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StartHome("", "");
                setVisible(false);
            }
        });
        add(BackButton);

        setVisible(true);
    }

    private void addCharacterPanel(ImageIcon icon, int x, int y, String text, String path) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 0, 0, 100, 100, this);
            }
        };

        panel.setLayout(null);
        panel.setBounds(x, y, 100, 180);
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel naming = new JLabel(text);
        naming.setBounds(20, 100, 100, 20);
        naming.setFont(new Font("Courier", Font.BOLD, 16));
        naming.setForeground(Color.GREEN);
        panel.add(naming);

        JButton buyButton = new JButton("Sel");
        buyButton.setBounds(20, 145, 60, 30);
        buyButton.setBackground(new Color(255, 165, 0));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.BOLD, 12));
        buyButton.setFocusable(false);
        buyButton.setBorderPainted(false);
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Config con =new Config();
                try {
                    String query = "UPDATE Elements set Enviro='" + path + "'  WHERE username='" + username + "' AND userpass='" + Password + "'";
                    con.statement.executeUpdate(query);
                }catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        panel.add(buyButton);

        add(panel);
    }

    public static void main(String[] args) {
        new EnviromtnFile("","");
    }
}
