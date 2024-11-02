import javax.swing.*;
import java.awt.*;


public class StartHome extends JFrame {
    ImageIcon characterIcon, roadIcon, coinsIcon, enviroIcon;
    String username;
    String Password;
    public StartHome(String username, String Password) {
        this.username = username;
        this.Password = Password;



        characterIcon = new ImageIcon(ClassLoader.getSystemResource("images/Character.jpg"));
        roadIcon = new ImageIcon(ClassLoader.getSystemResource("images/RoadAll.jpg"));
        coinsIcon = new ImageIcon(ClassLoader.getSystemResource("images/CoinsAll.png"));
        enviroIcon = new ImageIcon(ClassLoader.getSystemResource("images/Enviro2.jpg"));


        setTitle("Start");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        createStartButton();

        createImageButton(characterIcon, "Character", 50, 50, Color.CYAN);
        createImageButton(roadIcon, "Road", 300, 50, Color.GREEN);
        createImageButton(coinsIcon, "Coins", 50, 300, Color.YELLOW);
        createImageButton(enviroIcon, "Environment", 300, 300, Color.ORANGE);


        setVisible(true);
    }

    private void createStartButton() {
        JButton startButton = new JButton("Start");
        startButton.setBounds(200, 0, 200, 40); // Centered at the top with space
        startButton.setFocusable(false);
        startButton.setBackground(new Color(0, 128, 255)); // Game-like color (blue)
        startButton.setForeground(Color.WHITE); // Text color
        startButton.setFont(new Font("Arial", Font.BOLD, 18)); // Font style

        startButton.addActionListener(e -> {
            openOfficeFrame(username,Password);

           setVisible(false);
        });
        add(startButton);
    }
    public void  openOfficeFrame(String username,String Password) {
        JFrame officeFrame = new JFrame("Super Nova");
        officeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        officeFrame.add(new GameStation(username,Password));
        officeFrame.pack();
        officeFrame.setLocationRelativeTo(null);
        officeFrame.setVisible(true);
    }



    public void createImageButton(ImageIcon icon, String buttonText, int x, int y, Color buttonColor) {


        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(0, 0, 200, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, 200, 250);
        panel.add(imageLabel);


        JButton button = new JButton(buttonText);
        button.setBackground(buttonColor);
        button.setBounds(25, 210, 150, 30);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setOpaque(true);


        button.addActionListener(e -> {
           if(buttonText=="Character") {
           new CharacterFile(username,Password);
           setVisible(false);

           }else if(buttonText=="Road") {
               new RoadFile(username,Password);
               setVisible(false);
           }else if(buttonText=="Coins") {
               new CoinFile(username,Password);
               setVisible(false);
           }else if(buttonText=="Environment") {

               new EnviromtnFile(username,Password);
               setVisible(false);
           }
        });


        panel.add(button);
        add(panel);
    }

    public static void main(String[] args) {

        new StartHome("","");
    }

}
