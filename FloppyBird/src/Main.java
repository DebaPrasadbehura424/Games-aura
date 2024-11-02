import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        int boardWidth=360;
        int boardHeight=640;
        JFrame frame=new JFrame();
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        FlappyBird flappyBird=new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);

    }
}