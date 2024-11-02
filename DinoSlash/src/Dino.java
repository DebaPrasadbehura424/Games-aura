import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class Dino extends JPanel implements ActionListener, KeyListener {
    int panelWidth = 700;
    int panelHeight = 250;
    ImageIcon DinoImage;
    ImageIcon DinoDead;
    ImageIcon DinoJump;
    ImageIcon DinoDuck;
    ImageIcon Cactus1Image;
    ImageIcon Cactus2Image;
    ImageIcon Cactus3Image;




    static class Block {
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

    int dinoWidth = 88;
    int dinoHeight = 94;
    int dinoXAxis = 50;
    int dinoYAxis = panelHeight - dinoHeight;

    Block dinosaur;


    //cactus
    int cactus1Width=34;
    int cactus2Width=69;
    int cactus3Width=102;

    int cactusHeight=70;
    int cactusX=700;
    int cactusY=panelHeight-cactusHeight;
    ArrayList<Block> catusArray;




    //physics
    int velocityX=-12;
    int velocityY=0;
    int gravity= 1;

    boolean lamda=false;


    Timer gameLoop;
    Timer placeCactusTimer;

    boolean GameOver=false;
    int score=0;
    public Dino() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);


        DinoImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-run.gif")));
        DinoDead = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-dead.png")));
        DinoJump = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-jump.png")));
        DinoDuck=new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/dino-duck1.png")));
        Cactus1Image = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus1.png")));
        Cactus2Image = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus2.png")));
        Cactus3Image = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/cactus3.png")));

        if(!lamda){
            dinosaur = new Block(dinoXAxis, dinoYAxis, dinoWidth, dinoHeight, DinoImage);
        }else{
            dinosaur = new Block(dinoXAxis, dinoYAxis, dinoWidth, dinoHeight, DinoDuck);
        }

        catusArray = new ArrayList<Block>();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();



        //place cactus timer
        placeCactusTimer=new Timer(1500 ,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeCactus();
            }
        });
        placeCactusTimer.start();
    }

    void placeCactus() {
        if (GameOver){
            return;
        }
        double placeCactusChance=Math.random();
        if(placeCactusChance>.90){
            Block cactus=new Block(cactusX,cactusY,cactus3Width,cactusHeight,Cactus3Image);
            catusArray.add(cactus);
        }else if(placeCactusChance>.70){
            Block cactus=new Block(cactusX,cactusY,cactus2Width,cactusHeight,Cactus2Image);
            catusArray.add(cactus);
        }else if(placeCactusChance>.50){
            Block cactus=new Block(cactusX,cactusY,cactus1Width,cactusHeight,Cactus1Image);
            catusArray.add(cactus);

        }
        if(catusArray.size()>10){
            catusArray.removeFirst();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(dinosaur.img.getImage(), dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);

        for (Block cactus : catusArray) {
            g.drawImage(cactus.img.getImage(), cactus.x, cactus.y, cactus.width, cactus.height, null);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.PLAIN, 15));
        if(!GameOver){
            g.drawString("Score:" +" "+String.valueOf(score),10,35);
        }else{
            g.drawString("GameOver:" +" "+String.valueOf(score),10,35);
        }
    }





    //dinosaur move
    public  void move(){
        velocityY+=gravity;
        dinosaur.y += velocityY;
        if(dinosaur.y>dinoYAxis){
            dinosaur.y=dinoYAxis;
            velocityY=0;
            if(!lamda){
                dinosaur.img=DinoImage;
            }else{
                dinosaur.img=DinoDuck;
            }

        }
        //cactus
        for (Block cactus : catusArray) {
            cactus.x += velocityX;
            if (collision(dinosaur, cactus)) {
                GameOver=true;
                dinosaur.img=DinoDead;
            }
        }

        score++;


    }

    boolean collision(Block a ,Block b){
        return  a.x-15<b.x+b.width && a.x-15 + a.width>b.x && a.y+5<b.y+b.height && a.y+5 + a.height>b.y;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(GameOver){
            placeCactusTimer.stop();
            gameLoop.stop();
        }
    }




//key methods
    @Override
    public void keyTyped(KeyEvent e) {

        if(e.getKeyChar()=='s'){
            if(lamda){
                lamda=false;
            }else{
                lamda=true;
            }

         dinosaur.img=DinoDuck;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (dinosaur.y== dinoYAxis) {
                velocityY=-17;
                dinosaur.img=DinoJump;
            }
            if(GameOver){
                dinosaur.y=dinoYAxis;
                velocityY=0;
                dinosaur.img=DinoImage;
                catusArray.clear();
                score=0;
                GameOver=false;
                gameLoop.start();
                placeCactusTimer.start();
            }


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
