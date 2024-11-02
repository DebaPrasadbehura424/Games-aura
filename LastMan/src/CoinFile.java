import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinFile extends JFrame {
    ImageIcon GoldCoin, SilverCoin, BronzeCoin, DiamondCoin;
String username;
String Password;
    public CoinFile(String username, String Password) {
        this.username = username;
        this.Password = Password;

        GoldCoin = new ImageIcon(ClassLoader.getSystemResource("images/Coinc.png"));
        SilverCoin = new ImageIcon(ClassLoader.getSystemResource("images/CoinOne.png"));
        BronzeCoin = new ImageIcon(ClassLoader.getSystemResource("images/CoinTemple.png"));
        DiamondCoin = new ImageIcon(ClassLoader.getSystemResource("images/CoinSkull.png"));

        setLayout(null);

        JLabel titleLabel = new JLabel("Coins Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(200, 10, 200, 30);
        add(titleLabel);

        addCharacterPanel(GoldCoin, 20, 50, "Cole", "images/Coinc.png");
        addCharacterPanel(SilverCoin, 170, 50, "Gold", "images/CoinOne.png");
        addCharacterPanel(BronzeCoin, 320, 50, "Temple", "images/CoinTemple.png");
        addCharacterPanel(DiamondCoin, 470, 50, "Diamond", "images/CoinSkull.png");

        setTitle("Coins Store");
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

    private void addCharacterPanel(ImageIcon icon, int x, int y, String text, String path) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 3, 0, 100, 100, this);
            }
        };

        panel.setLayout(null);
        panel.setBounds(x, y, 110, 180);
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
                    String query = "UPDATE Elements set Coins='" + path + "'  WHERE username='" + username + "' AND userpass='" + Password + "'";
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
        new CoinFile("","");
    }
}
