import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoadFile extends JFrame {
    String username="";
    String Password="";
    public RoadFile(String username,String Password) {
        this.username=username;
        this.Password=Password;

        setTitle("Road Store");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(50, 50, 50));

        addCharacterPanel("Hill", "images/Hill.png", 10, 10);
        addCharacterPanel("Road", "images/ice.png", 450, 10);
        addCharacterPanel("Mountain", "images/Magma.png", 10, 180);
        addCharacterPanel("Valley", "images/GrassRoad.png", 450, 180);
        addCharacterPanel("Forest", "images/GreenRoad.png", 10, 350);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(350, 500, 100, 30);
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

    private void addCharacterPanel(String name, String imagePath, int x, int y) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, 300, 150);
        panel.setLayout(null);
        panel.setBackground(new Color(70, 70, 70));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        ImageIcon roads = new ImageIcon(ClassLoader.getSystemResource(imagePath));
        Image image = roads.getImage().getScaledInstance(250, 70, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);
        JLabel photo = new JLabel(icon);
        photo.setBounds(20, 30, 250, 70);
        panel.add(photo);

        JLabel labelName = new JLabel(name);
        labelName.setBounds(20, 115, 200, 20);
        labelName.setFont(new Font("Courier", Font.BOLD, 17));
        labelName.setForeground(Color.CYAN);
        panel.add(labelName);

        JButton buyButton = new JButton("Sel");
        buyButton.setBounds(160, 115, 80, 20);
        buyButton.setBackground(new Color(100, 149, 237));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.BOLD, 12));
        buyButton.setFocusable(false);
        buyButton.setBorderPainted(false);
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Config con =new Config();
                try {
                    String query = "UPDATE Elements set Roadi='" + imagePath + "'  WHERE username='" + username + "' AND userpass='" + Password + "'";
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
        SwingUtilities.invokeLater(() -> new RoadFile("",""));
    }
}
