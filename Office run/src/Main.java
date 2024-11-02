import javax.swing.*;


public class Main {
    public static void main(String[] args)  {
        int boardWidth=700;
        int boardHeight=250;

        JFrame frame =new JFrame();
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        Office office=new Office("");
        frame.add(office);
        frame.pack();
        office.requestFocus();
        frame.setVisible(true);
    }
}