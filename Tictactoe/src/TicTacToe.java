
import java.awt.*;
import java.util.Objects;
import javax.swing.*;


public class TicTacToe {
    int boardWidth=600;
    int boardHeight=700;
    JFrame frame=new JFrame("Tic-Tac-Toe");
    JLabel textLabel1= new JLabel();
    JPanel textPanel1= new JPanel();
    JPanel BoardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton [][] Boardbuttons=new JButton[3][3];
    JButton reset= new JButton("Reset");

    String playerX="X";
    String PlayerO="O";
    String currentPlayer=playerX;
    boolean GameOver=false;



    TicTacToe(){
        reset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





        //ask chatgpt about this block
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        //ask chatgpt about this block




        //text  or labels
        textLabel1.setBackground(Color.darkGray);
        textLabel1.setForeground(Color.white);
        textLabel1.setFont(new Font("Arial",Font.BOLD,50));



        //ask chatgpt about this block
        textLabel1.setHorizontalAlignment(JLabel.CENTER);
        textLabel1.setOpaque(true);
        //ask chatgpt about this block




        textLabel1.setText("TicTacToe");
        //ask chatgpt about this block
        textPanel1.setLayout(new BorderLayout());
        //ask chatgpt about this block
        textPanel1.add(textLabel1);

        frame.add(textPanel1,BorderLayout.NORTH);
//        buttons reset declaration
        reset.setSize(100,20);
        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.add(reset);
        frame.add(buttonPanel,BorderLayout.SOUTH);

        reset.addActionListener(e -> {
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    Boardbuttons[i][j].setText("");
                    GameOver=false;
                    textLabel1.setText("TicTacToe");
                    setReset(Boardbuttons[i][j]);
                }
            }
        });

        //here we define our Board
        int BoxW=3;
        int BoxH=3;
        BoardPanel.setLayout(new GridLayout(BoxW,BoxH));
        BoardPanel.setBackground(Color.darkGray);
        frame.add(BoardPanel);


        //now we are going create a tile using button

        for(int r=0;r<3;r++){
            for(int c =0;c<3;c++){

                JButton tiles=new JButton();
                tiles.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Boardbuttons[r][c]=tiles;
                BoardPanel.add(tiles);
                tiles.setBackground(Color.darkGray);
                tiles.setForeground(Color.white);
                tiles.setFont(new Font("Arial",Font.BOLD,50));
                tiles.setFocusable(false);

                //add click text catch function
                tiles.addActionListener(e -> {
                    if (GameOver) {
                        return;
                    }
                    JButton tile = (JButton) e.getSource();
                    if (tile.getText().isEmpty()) {
                        tile.setText(currentPlayer);
                        checkWinner();
                        if (!GameOver) {
                            currentPlayer = currentPlayer.equals(playerX) ? PlayerO : playerX;
                        }
                    }
                });
            }
        }
    }
    void checkWinner(){
        //rows
        for(int r=0;r<3;r++){
            if(Objects.equals(Boardbuttons[r][0].getText(), "")) continue;
            if(Objects.equals(Boardbuttons[r][0].getText(), Boardbuttons[r][1].getText())
               && Objects.equals(Boardbuttons[r][1].getText(), Boardbuttons[r][2].getText())) {
                for(int i=0;i<3;i++){
                    setWinner(Boardbuttons[r][i]);
                }
                GameOver=true;
                return;
            }
        }
        //cols
            for(int c=0;c<3;c++) {
                if (Objects.equals(Boardbuttons[0][c].getText(), "")) continue;
                if (Objects.equals(Boardbuttons[0][c].getText(), Boardbuttons[1][c].getText())
                        && Objects.equals(Boardbuttons[1][c].getText(), Boardbuttons[2][c].getText())) {
                    for(int i=0;i<3;i++) {
                        setWinner(Boardbuttons[i][c]);
                    }
                    GameOver = true;
                    return;
                }
            }
            //diagonal 1

        if(Objects.equals(Boardbuttons[0][0].getText(), Boardbuttons[1][1].getText())
                && Objects.equals(Boardbuttons[1][1].getText(), Boardbuttons[2][2].getText())
                && !Objects.equals(Boardbuttons[0][0].getText(), "")) {
            for (int i=0;i<3;i++) {
                setWinner(Boardbuttons[i][i]);
            }
            GameOver=true;
               return;
        }
        //diagonal 2
        if(Objects.equals(Boardbuttons[0][2].getText(), Boardbuttons[1][1].getText())
                &&
                Objects.equals(Boardbuttons[1][1].getText(), Boardbuttons[2][0].getText())
                && !Objects.equals(Boardbuttons[0][2].getText(), "")) {
           setWinner(Boardbuttons[0][2]);
           setWinner(Boardbuttons[1][1]);
           setWinner(Boardbuttons[2][0]);
            GameOver=true;
        }
    }

    void setWinner(JButton tile){
        tile.setBackground(Color.green);
        textLabel1.setText(currentPlayer + " is winner");
    }
    void setReset(JButton tile){
        tile.setBackground(Color.darkGray);
        tile.setForeground(Color.white);
    }



}