import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TicTacToeBoard board = new TicTacToeBoard(3,3);
        add(board);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}