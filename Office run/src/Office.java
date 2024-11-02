import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class Office extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 700;
    int boardHeight = 250;
    ImageIcon ronny;
    ImageIcon road;
    ImageIcon firstRoad;
    ImageIcon Ninja;
    ImageIcon Back;

    // Soil parameters
    int soilWidth = 100;
    int soilHeight = 20;
    int soilY = boardHeight - soilHeight;

    class Soil {
        int x;
        int y;
        int width;
        int height;
        Image img;

        Soil(Image img) {
            this.img = img;
            this.x = boardWidth; // Start off the right side of the screen
            this.y = soilY; // Set the y position
            this.width = soilWidth;
            this.height = soilHeight;
        }
    }

    // Runner parameters
    int charWidth = 90;
    int charHeight = 90;
    int charX = 50;
    int charY = soilY - charHeight;
    int limit = soilY - charHeight;

   static class  Block {
        int x;
        int y;
        int width;
        int height;
        ImageIcon img;

        Block(int x, int y, int width, int height, ImageIcon img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    Block person;
    ArrayList<Soil> soils;



    Timer gameLoop;
    Timer placeRoadTimer;

    // Gravity
    int gravity = 1;
    int velocityX = -6;
    int velocityY = 0;

    int CharVelocity = 60;

    int spaceX = 0;
    boolean drawFirstRoad = true;
    boolean personImage = true;
    boolean gameOver = false;

    JButton restartButton;
    int score = 0; // Score variable



       String usename;
       int highScores;
       Link li;
       public  Office(String username) {
       this.usename = username;
           li = new Link();


        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        ronny = new ImageIcon(Objects.requireNonNull(getClass().getResource("./objects/jhon.gif")));
        road = new ImageIcon(Objects.requireNonNull(getClass().getResource("./objects/Road.png")));
        firstRoad = new ImageIcon(Objects.requireNonNull(getClass().getResource("./objects/Road.png")));
        Ninja = new ImageIcon(Objects.requireNonNull(getClass().getResource("./objects/ninja.png")));
        person = new Block(charX, charY, charWidth, charHeight, ronny);
        Back=new ImageIcon(Objects.requireNonNull(getClass().getResource("./objects/Back2.jpg")));


        soils = new ArrayList<>();

        // Initialize restart button
        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(80, 30));
        restartButton.setBounds(boardWidth - 100, 10, 80, 30);
        restartButton.addActionListener(e -> restartGame());
        restartButton.setFocusable(false);
        restartButton.setVisible(false); // Initially hidden

        // Add button to the panel
        this.setLayout(null); // Use absolute positioning
        this.add(restartButton);

        placeRoadTimer = new Timer(1000, e -> placeRoads());


        gameLoop = new Timer(1000 /60, this);
        gameLoop.start();
        placeRoadTimer.start();
    }

    public void placeRoads() {
        Soil path;
        double placeCactusChance = Math.random();
        if (placeCactusChance > .90) {
            path = new Soil(road.getImage());
            soils.add(path);
        } else if (placeCactusChance > .45) {
            path = new Soil(road.getImage());
            soils.add(path);
        } else if (placeCactusChance > .20) {
            path = new Soil(road.getImage());
            soils.add(path);
        }
    }

    public void move() {
        velocityY += gravity;
        for (int i = 0; i < soils.size(); i++) {
            Soil soil = soils.get(i);
            soil.x += velocityX;
            if (soil.x + soil.width < 0) {
                soils.remove(i);
                i--;
            }
        }
        spaceX += velocityX;
        if (spaceX + boardWidth + 50 < 0 && drawFirstRoad) {
            drawFirstRoad = false;
        }
        for (Soil pathCk : soils) {
            if (collision(person, pathCk) || CollisionFirst(person, firstRoad)) {
                person.y = limit;
                personImage = true;
                score++;

            } else {
                person.y += 1;
            }
        }
        if (person.y >= boardHeight - 100 || person.y <= 0) {
            gameOver = true;
        }
        if (gameOver) {
            gameLoop.stop();
            placeRoadTimer.stop();
            restartButton.setVisible(true); // Show the restart button
        }
    }

    int check;
    private void restartGame() {

        highScores=score;
        check=li.getHighScore(usename);
        updateScore(usename);
        person.y = limit;
        velocityY = 0;
        gameOver = false;
        soils.clear();
        drawFirstRoad = true;
        spaceX = 0;
        personImage = true;
        score = 0;

        // Restart the game loop and place road timer

        gameLoop.start();
        placeRoadTimer.start();
        restartButton.setVisible(false); // Hide the restart button after restarting
    }

    public void updateScore(String username) {
        if (highScores > check) {
            li.updateHighScore(username, highScores);
        }else{
            System.out.println("this is your high score" + highScores);
        }
    }


    boolean CollisionFirst(Block a, ImageIcon b) {
        return a.x - 15 < spaceX + boardWidth + 50 &&
                a.x - 15 + a.width > spaceX &&
                a.y + 5 < boardHeight - 20 + 20 &&
                a.y + 5 + a.height > boardHeight - 20;
    }

    boolean collision(Block a, Soil b) {
        boolean collidesHorizontally = a.x < b.x + b.width && a.x + a.width > b.x;
        boolean collidesVertically = a.y + a.height >= b.y && a.y + a.height <= b.y + 5; // Adjust 5 as needed
        return collidesHorizontally && collidesVertically;
    }

    // Draw my pictures
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(Back.getImage(),0,0,boardWidth,boardHeight,null);
        if (personImage) {
            g.drawImage(person.img.getImage(), person.x, person.y, person.width, person.height, null);
        } else {
            g.drawImage(Ninja.getImage(), person.x, person.y, person.width, person.height, null);
        }

        if (drawFirstRoad) {
            g.drawImage(firstRoad.getImage(), spaceX, boardHeight - 20, boardWidth + 50, 20, null);
        }
        for (Soil soil : soils) {
            g.drawImage(soil.img, soil.x, soil.y, soil.width, soil.height, null);
        }

        // Draw score
        g.setColor(Color.BLACK); // Set the color for the score text
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size
        g.drawString("Score: " + score, 10, 20);

        g.setColor(Color.BLACK); // Set the color for the score text
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("HighScore: " + li.getHighScore(usename), 300, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (person.y < 140) {
                return;
            }
            person.y -= CharVelocity;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            personImage = false;
            person.y -= CharVelocity + 20;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new Office("");
    }
}
