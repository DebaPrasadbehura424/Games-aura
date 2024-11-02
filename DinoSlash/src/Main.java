

import javax.swing.*;


public class Main {
    public static void main(String[] args) throws Exception {
        int frameWidth =700;
        int frameHeight =250;
       JFrame frame = new JFrame("T-rex-Run");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setSize(frameWidth, frameHeight);
       frame.setLocationRelativeTo(null);
       Dino d= new Dino();
       frame.add(d);
       frame.pack();
       d.requestFocus();
       frame.setVisible(true);

    }
}