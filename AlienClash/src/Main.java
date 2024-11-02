import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int tileSize=32;
        int rows=16;
        int cols=16;
        int boardWidth=cols*tileSize;//512 px
        int boardHeight=rows*tileSize;
        JFrame frame=new JFrame("Space Invaders");
        frame.setSize(boardWidth,boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Space d = new Space();
        frame.add(d);
        frame.requestFocus();
        frame.pack();
        frame.setVisible(true);

    }
}