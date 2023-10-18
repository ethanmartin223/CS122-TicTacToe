import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class MainWindow extends JFrame {

    JRadioButton playerStarting, computerStarting;
    JSlider selectedBoardSizeSlider;

    public MainWindow() {
        //window settings
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel startingPlayerContainer = new JPanel();
        ButtonGroup startingPlayer = new ButtonGroup();

        playerStarting = new JRadioButton("Player");
        computerStarting = new JRadioButton("Computer");

        JLabel startingPlayerLabel = new JLabel("Starting Player: ");
            startingPlayer.add(playerStarting);
            startingPlayer.add(computerStarting);
            startingPlayerContainer.add(startingPlayerLabel);
            startingPlayerContainer.add(playerStarting);
            startingPlayerContainer.add(computerStarting);
            playerStarting.doClick();
        add(startingPlayerContainer);

        JPanel selectedBoardSizeContainer = new JPanel();
            selectedBoardSizeSlider = new JSlider(0, 200, 120);
            selectedBoardSizeSlider.setPaintTrack(true);
            selectedBoardSizeSlider.setPaintTicks(true);
            selectedBoardSizeSlider.setPaintLabels(true);
            selectedBoardSizeSlider.setMajorTickSpacing(50);
            selectedBoardSizeSlider.setMinorTickSpacing(5);
            selectedBoardSizeSlider.addChangeListener(this::onSliderChange);
        add(selectedBoardSizeContainer);
        

//        TicTacToeBoard board = new TicTacToeBoard(3,3, false, TicTacToeBoard.X);
//        add(board, BorderLayout.CENTER);
//
//        JButton newGameButton = new JButton("New Game");
//        newGameButton.addActionListener(e->board.resetGame());
//        add(newGameButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }

    public void onSliderChange(ChangeEvent e) {

    }
}