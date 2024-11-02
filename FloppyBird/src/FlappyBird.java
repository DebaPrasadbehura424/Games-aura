import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener,KeyListener {
int boardWidth=360;
int boardHeight=640;

    //images declarations

    Image FlappyBg;
    Image FlappyTop;
    Image FlappyBottom;
    Image Flappy;

    //Bird
    int BirdX=boardWidth/8;
    int BirdY=boardHeight/2;
    int BirdWidth=34;
    int BirdHeight=24;




    class Bird{
        int x =BirdX;
        int y =BirdY;
        int width=BirdWidth;
        int height=BirdHeight;
        Image img;
        Bird(Image img){
            this.img=img;
        }

    }
    //pipes
    int pipeX=boardWidth;
    int pipeY=0;
    int pipeWidth=64;
    int pipeHeight=512;
    class Pipe{
        int x=pipeX;
        int y=pipeY;
        int width=pipeWidth;
        int height=pipeHeight;
        Image img;
        boolean passed=false;

        Pipe(Image img){
            this.img=img;
        }

    }



    //game logic
    Bird bird;


    //timer
    Timer gameLoop;
    Timer PlacepipeTimer;
    boolean gameOver=false;
    double score =0;

    //velocity & gravity
    int velocityY=0;
    int velocityX=-6;
    int gravity=1;

    ArrayList<Pipe>pipes;
    Random random = new Random();




FlappyBird(){
    setPreferredSize(new Dimension(boardWidth,boardHeight));
    setFocusable(true);
    addKeyListener(this);

    //image define
    FlappyBg = new ImageIcon(Objects.requireNonNull(getClass().getResource("flappybirdbg.png"))).getImage();
    FlappyTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("toppipe.png"))).getImage();
    FlappyBottom = new ImageIcon(Objects.requireNonNull(getClass().getResource("bottompipe.png"))).getImage();
    Flappy = new ImageIcon(Objects.requireNonNull(getClass().getResource("FlappyBird.png"))).getImage();

    bird= new Bird(Flappy);

    pipes = new ArrayList<Pipe>();
    //timer declaration
    PlacepipeTimer = new Timer(1500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            placePipes();
        }
    });
    gameLoop = new Timer(1000/60,this);
    gameLoop.start();
    PlacepipeTimer.start();


}


    public  void placePipes(){
    int randomPipeY= (int) (pipeY-pipeHeight/4-Math.random()*(pipeHeight/2));
    int openingHeight=boardHeight/4;
    Pipe toppipe=new Pipe(FlappyTop);
    toppipe.y=randomPipeY;
    pipes.add(toppipe);

    Pipe bottompipe= new Pipe(FlappyBottom);
    bottompipe.y=toppipe.y+openingHeight + pipeHeight;
    pipes.add(bottompipe);
    }




    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g ){
        g.drawImage(FlappyBg,0,0,boardWidth,boardHeight,null);
        g.drawImage(bird.img,bird.x, bird.y,bird.width,bird.height,null);
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Courier",Font.PLAIN,20));
        if(gameOver){

            g.drawString("GameOver: "+(int)score,20,30);
        }else{
            g.drawString("Score: "+(int)score,20,32);
        }


    }


    public void move(){
      velocityY+=gravity;
      bird.y+=velocityY;
      bird.y=Math.max(bird.y,0);
      for (Pipe pipe : pipes) {
          pipe.x+=velocityX;
          if(!pipe.passed && bird.x>pipe.x+pipe.width){
              pipe.passed=true;
              score+=0.5;
          }
          if(collision(bird,pipe)){
              gameOver=true;
          }
      }
      if(bird.y>boardHeight){
          gameOver=true;
      }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     move();
     repaint();
     if(gameOver){
         PlacepipeTimer.stop();
         gameLoop.stop();
     }
    }
    public boolean collision(Bird a , Pipe p ){
    return  a.x<p.x+ p.width  &&
            a.x+a.width>p.x &&
            a.y<p.y+ p.height &&
            a.y+a.height>p.y;
    }



//key event
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_SPACE){
          velocityY-=9;
          if(gameOver){
             bird.y=BirdY;
             velocityY=0;
             pipes.clear();
             score=0;
             gameOver=false;
             gameLoop.start();
             PlacepipeTimer.start();
          }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
