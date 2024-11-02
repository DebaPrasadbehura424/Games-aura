import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Snake extends JPanel implements ActionListener,KeyListener {


    private class Tile{
        int x,y;
        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }



    int boardWidth;
    int boardHeight;
    int tileSize=25;
    Tile snakeHead,snakeFood;
    ArrayList<Tile> snakeBody;

    Random random;

    //timer
    Timer gameLoop;

    int velocityY=0;
    int velocityX=0;

    boolean gameOver=false;

    int score=0;




    Snake( int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight =boardHeight;
        setPreferredSize(new Dimension( this.boardWidth,this.boardHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);


        snakeHead= new Tile(5,5);

        snakeBody=new ArrayList<Tile>();

        snakeFood= new Tile(10,10);
        random = new Random();
        placeFood();
        gameLoop = new Timer(100,this);
        gameLoop.start();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
//        for(int i=0;i<boardWidth/tileSize;i++){
//            for(int j=0;j<boardHeight/tileSize;j++){
//                g.drawRect(i*tileSize,j*tileSize,tileSize,tileSize);
//            }
//        }

        g.setColor(Color.RED);
        g.fillRect(snakeFood.x*tileSize, snakeFood.y*tileSize,tileSize,tileSize);

        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize,tileSize,tileSize);


        for(int i=0;i<snakeBody.size();i++){
            Tile tileadd = snakeBody.get(i);
            g.fillRect(tileadd.x*tileSize, tileadd.y*tileSize, tileSize,tileSize);
        }

        g.setFont(new Font("Arial",Font.PLAIN,20));
        if(gameOver){
            g.setColor(Color.RED);
            g.drawString("Game Over:" + score,10,20);
        }
        else {
            g.setColor(Color.GREEN);
            g.drawString("score:" + score,10,20);
        }
    }

    public void placeFood(){

         snakeFood.x=random.nextInt(boardWidth/tileSize);
         snakeFood.y=random.nextInt(boardHeight/tileSize);
    }
    public boolean collision(Tile t1,Tile t2){
        return t1.x==t2.x && t1.y== t2.y ;

    }



   public void move(){
        if(collision(snakeHead,snakeFood)){
            snakeBody.add(new Tile(snakeFood.x,snakeFood.y));
            score++;

            placeFood();
        }
        for(int i=snakeBody.size()-1;i>=0;i--){
            Tile tileadd = snakeBody.get(i);
            if(i==0){
                tileadd.x=snakeHead.x;
                tileadd.y=snakeHead.y;
            }else{
                Tile tileadd1 = snakeBody.get(i-1);
                tileadd.x=tileadd1.x;
                tileadd.y=tileadd1.y;
            }
        }
        
        
        snakeHead.x+=velocityX;
        snakeHead.y+=velocityY;

        for(int i=0;i<snakeBody.size();i++){
            Tile tileadd = snakeBody.get(i);
            if(collision(tileadd,snakeHead)){
                gameOver=true;
            }
        }

        if(snakeHead.x*tileSize<0 ||
                snakeHead.x*tileSize>boardWidth ||
                snakeHead.y*tileSize<0 ||
                snakeHead.y*tileSize>boardHeight){
            gameOver=true;

        }
   }


     //actions
    @Override
    public void actionPerformed(ActionEvent e) {
     move();
     repaint();
     if(gameOver){
         gameLoop.stop();
     }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN && velocityY!=-1){

            velocityY=1;
            velocityX=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP && velocityY!=1){

            velocityY=-1;
            velocityX=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT && velocityX!=1){

            velocityX=-1;
            velocityY=0;
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX!=-1){


            velocityX=1;
            velocityY=0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_ENTER){
            gameOver = false;
            score = 0;
            snakeBody.clear();
            snakeHead = new Tile(5, 5);
            placeFood();
            velocityX = 0;
            velocityY = 0;
            gameLoop.start();
            repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
