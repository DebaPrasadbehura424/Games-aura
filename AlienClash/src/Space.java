import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class Space extends JPanel implements ActionListener, KeyListener {




    class Block {
        int x;
        int y;
        int width;
        int height;
        Image img;
        boolean alive = true; // use for aliens
        boolean use = false; // use for bullets

        Block(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    int tileSize = 32;
    int rows = 16;
    int cols = 16;
    int boardWidth = cols * tileSize; // 512 px
    int boardHeight = rows * tileSize;

    int spaceWidth = tileSize * 2;
    int spaceHeight = tileSize;
    int SpaceshipX = tileSize * cols / 2 - tileSize;
    int SpaceshipY = boardHeight - tileSize * 2;
    int spaceVelocity=tileSize;

    // Images declaration
    Image spaceshipImage;
    Image alienImage;
    ArrayList<Image> alienImagesArray;

    Block bl;


    ArrayList<Block> alienArray;
    int alienWidth=tileSize*2;
    int alienHeight=tileSize;
    int alienX=tileSize;
    int alienY=tileSize;

    int alienRows=2;
    int alienCols=3;
    int alienCount=0;
    int alienVelocityX=1;





    // Loop
    Timer gameLoop;





    // Constructor
    public Space() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        spaceshipImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("spaceship.png"))).getImage();
        alienImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("Aliens.png"))).getImage();

        // Array add
        alienImagesArray = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            alienImagesArray.add(alienImage);
        }

        bl = new Block(SpaceshipX, SpaceshipY, spaceWidth, spaceHeight, spaceshipImage);
        alienArray = new ArrayList<>();


        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start(); // Start the timer
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(bl.img, bl.x, bl.y, bl.width, bl.height, null);
    }

    //key types
    @Override
    public void actionPerformed(ActionEvent e) {
        // Update game state if needed
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT && bl.x-spaceVelocity>=0) {
            bl.x -= spaceVelocity;

        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && bl.x+bl.width+spaceVelocity<=boardWidth) {
            bl.x += spaceVelocity;
        }
    }

}
