import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;



public class WholeMock {
    //declare all components
    int width=600;
    int height=650;
    JFrame frame= new JFrame("WholeMock");
    JLabel text1= new JLabel();
    JPanel textPanel= new JPanel();
    JPanel BoardPanel= new JPanel();
    JButton [] Tile= new JButton[9];
    //first time image create
    ImageIcon RedHuman;
    ImageIcon BlueHuman;


    JButton redHat;
    JButton blueHat;

    Timer setRedTimer;
    Timer setBlueTimer;

    Random random=new Random();
    int score=0;

    WholeMock(){
//      frame setup
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setResizable(false);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());


        //text setup

        text1.setText("SCORE : 0");
        text1.setOpaque(true);
        text1.setHorizontalAlignment(JLabel.CENTER);
        text1.setForeground(Color.gray);
        text1.setFont(new Font("Arial", Font.PLAIN, 50));

        //panel for text setup
        textPanel.setLayout(new BorderLayout());

        //add text in panel
        textPanel.add(text1);

        //add panel in frame
        frame.add(textPanel, BorderLayout.NORTH);

        //BoardPanel setups
        BoardPanel.setLayout(new GridLayout(3,3));
        frame.add(BoardPanel);

        //load images
        Image RedKing=new ImageIcon(Objects.requireNonNull(getClass().getResource("./Red.png"))).getImage();
        RedHuman=new ImageIcon(RedKing.getScaledInstance(150,150,Image.SCALE_SMOOTH));
        Image BluKing= new  ImageIcon(Objects.requireNonNull(getClass().getResource("./White.png"))).getImage();
        BlueHuman= new ImageIcon(BluKing.getScaledInstance(150,150,Image.SCALE_SMOOTH));

        for(int i=0;i<9;i++){
           JButton tile= new JButton();
           tile.setFocusable(false);
           //ask to chatgpt
           Tile[i]=tile;
           BoardPanel.add(tile);
           tile.addActionListener(e -> {
          JButton tile1 =(JButton) e.getSource();
          if(tile1 ==redHat){
              score +=10;
              text1.setText("SCORE : "+score);
          }else if(tile1 ==blueHat){
              text1.setText("Game over");
              setBlueTimer.cancel();
              setRedTimer.cancel();
              for(int i1 = 0; i1 <9; i1++){
              Tile[i1].setEnabled(false);
              }
          }
           });
        }

        setRedTimer=new Timer();
        TimerTask setRedTimerTask=new TimerTask() {
            public void run() {
                if(redHat !=null){
                    redHat.setIcon(null);
                    redHat=null;
                }
                int num= random.nextInt(9);
                redHat= Tile[num];
                if(blueHat==Tile[num]){return;}
                redHat.setIcon(RedHuman);
            }
        };
        setRedTimer.schedule(setRedTimerTask,0,1000);

        setBlueTimer=new Timer();
        TimerTask setBlueTimerTask=new TimerTask() {
            public void run() {
                if(blueHat !=null){
                    blueHat.setIcon(null);
                    blueHat=null;
                }
                int num= random.nextInt(9);
                blueHat= Tile[num];
                if(redHat==Tile[num]){return;}
                blueHat.setIcon(BlueHuman);
            }
        };
        setBlueTimer.schedule(setBlueTimerTask,0,1000);




        frame.setVisible(true);
    }

}
