import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterFile extends JFrame {
    ImageIcon Jack, Daniel, Wllet, Ronny, Reilay, Jhon;
     String username="";
     String Password="";
    public CharacterFile(String username, String Password) {
        this.username=username;
        this.Password=Password;

        Jack = new ImageIcon(ClassLoader.getSystemResource("images/Jack.gif"));
        Daniel = new ImageIcon(ClassLoader.getSystemResource("images/Daniel.gif"));
        Wllet = new ImageIcon(ClassLoader.getSystemResource("images/Wllet.gif"));
        Ronny = new ImageIcon(ClassLoader.getSystemResource("images/Ronny.gif"));
        Reilay = new ImageIcon(ClassLoader.getSystemResource("images/Reilay.gif"));
        Jhon = new ImageIcon(ClassLoader.getSystemResource("images/Jhon.gif"));

        setLayout(null);

        JLabel titleLabel = new JLabel("Character Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 10, 200, 30);
        add(titleLabel);

        addCharacterPanel(Jack, 20, 50, "Jack","images/Jack.gif");
        addCharacterPanel(Daniel, 170, 50, "Daniel","images/Daniel.gif");
        addCharacterPanel(Wllet, 320, 50, "Wllet","images/Wllet.gif");
        addCharacterPanel(Ronny, 470, 50, "Ronny","images/Ronny.gif");
        addCharacterPanel(Reilay, 20, 250, "Reilay","images/Reilay.gif");
        addCharacterPanel(Jhon, 170, 250, "Jhon","images/Jhon.gif");

        setTitle("Character Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(50, 50, 50));

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(250, 500, 100, 30);
        BackButton.setBackground(new Color(150, 149, 237));
        BackButton.setForeground(Color.WHITE);
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

    private void addCharacterPanel(ImageIcon icon, int x, int y, String text,String path) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 0, 0, 100, 100, this);
            }
        };

        panel.setLayout(null);
        panel.setBounds(x, y, 100, 180);
        panel.setBackground(new Color(70, 70, 70));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel naming = new JLabel(text);
        naming.setBounds(20, 100, 100, 20);
        naming.setFont(new Font("Courier", Font.BOLD, 17));
        naming.setForeground(Color.CYAN);
        panel.add(naming);

        JButton buyButton = new JButton("Sel");
        buyButton.setBounds(20, 145, 60, 30);
        buyButton.setBackground(new Color(100, 149, 237));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.BOLD, 12));
        buyButton.setFocusable(false);
        buyButton.setBorderPainted(false);
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Config con =new Config();
                try {
                    String query = "UPDATE Elements set Charcter='" + path + "'  WHERE username='" + username + "' AND userpass='" + Password + "'";
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
        new CharacterFile("","");
    }
}
