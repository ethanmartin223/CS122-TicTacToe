import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TicTacToeBoard board = new TicTacToeBoard(3,3, true, TicTacToeBoard.X);

//        board.debugSetBoardState(new byte[][] {
//                {0,2,1},
//                {0,2,0},
//                {1,0,0},
//        }); //DEBUG

        add(board, BorderLayout.CENTER);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e->board.resetGame());
        add(newGameButton, BorderLayout.SOUTH);

        JLabel winnerText = new JLabel("Testing");
        add(winnerText, BorderLayout.NORTH);


        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}