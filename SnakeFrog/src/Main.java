
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
    int boardWidth=600;
    int boardHeight=600;

    JFrame frame=new JFrame("Snake Frog");
    frame.setSize(boardWidth,boardHeight);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);
    Snake snakeGame= new Snake(boardWidth,boardHeight);
    frame.add(snakeGame);
    snakeGame.requestFocus();
    frame.pack();

    }
}