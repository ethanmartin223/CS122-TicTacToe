import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

    JRadioButton playerStarting, computerStarting;
    JSlider selectedBoardSizeSlider, selectedAiDiffucultySlider;
    JPanel menuUI, gameOverButtons;
    TicTacToeBoard ticTacToeBoard;
    JLabel updateLabel;
    JButton newGameButton, backToMenuButton;

    public MainWindow() {
        //window settings
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Main Menu
        menuUI = new JPanel();
        menuUI.setLayout(new BoxLayout(menuUI, BoxLayout.Y_AXIS));

            //Title label for menu
            JLabel titleLabel = new JLabel("Tic-Tac-Toe");
            titleLabel.setFont(new Font("arial", Font.BOLD, 80));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setBorder(
                    BorderFactory.createEmptyBorder(30,0,60,0));
            menuUI.add(titleLabel);

            //Options Label menu
            JLabel optionsLabel = new JLabel("Options");
            optionsLabel.setFont(new Font("arial", Font.BOLD, 40));
            optionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuUI.add(optionsLabel);

            //Starting Player Panel
            JPanel startingPlayerContainer = new JPanel();
                ButtonGroup startingPlayer = new ButtonGroup();
                playerStarting = new JRadioButton("Player");
                computerStarting = new JRadioButton("Computer");
                playerStarting.setFocusPainted(false);
                computerStarting.setFocusPainted(false);

                JLabel startingPlayerLabel = new JLabel("Starting Player: ");
                startingPlayer.add(playerStarting);
                startingPlayer.add(computerStarting);
                startingPlayerContainer.add(startingPlayerLabel);
                startingPlayerContainer.add(playerStarting);
                startingPlayerContainer.add(computerStarting);
                playerStarting.doClick();
            menuUI.add(startingPlayerContainer);

            //Board size selector Panel
            JPanel selectedAiDifficultyContainer = new JPanel();
                JLabel aiDifficultySliderLabel = new JLabel("Computer Difficulty: ");
                selectedAiDiffucultySlider = new JSlider(1, 10, 10);
                selectedAiDiffucultySlider.setPaintLabels(true);
                selectedAiDiffucultySlider.setMajorTickSpacing(1);
                selectedAiDiffucultySlider.setMinorTickSpacing(2);
                selectedAiDiffucultySlider.setMaximumSize(new Dimension(300, 50));
                selectedAiDiffucultySlider.addChangeListener(this::onAIDifficultySliderChange);
            selectedAiDifficultyContainer.add(aiDifficultySliderLabel);
            selectedAiDifficultyContainer.add(selectedAiDiffucultySlider);
            menuUI.add(selectedAiDifficultyContainer);

            //Board size selector Panel
            JPanel selectedBoardSizeContainer = new JPanel();
                JLabel sliderLabel = new JLabel("Board Size: ");
                selectedBoardSizeSlider = new JSlider(3, 5, 3);
                selectedBoardSizeSlider.setPaintLabels(true);
                selectedBoardSizeSlider.setMajorTickSpacing(1);
                selectedBoardSizeSlider.setMinorTickSpacing(1);
                selectedBoardSizeSlider.setMaximumSize(new Dimension(300, 50));
                selectedBoardSizeSlider.addChangeListener(this::onBoardSizeSliderChange);
            selectedBoardSizeContainer.add(sliderLabel);
            selectedBoardSizeContainer.add(selectedBoardSizeSlider);
            menuUI.add(selectedBoardSizeContainer);

            //Start Game button
            JButton startGameButton = new JButton("  Start Game  ");
            startGameButton.addActionListener(this::startGame);
            startGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            startGameButton.setFont(new Font("arial", Font.BOLD, 35));
            startGameButton.setOpaque(false);
            startGameButton.setContentAreaFilled(false);
            startGameButton.setBorderPainted(false);
            startGameButton.setFocusPainted(false);
            startGameButton.setBorder(BorderFactory.createEmptyBorder(50,0, 0, 0));
            menuUI.add(startGameButton);

        add(menuUI, BorderLayout.PAGE_START);
        setVisible(true);
    }

    private void startGame(ActionEvent actionEvent) {
        menuUI.setVisible(false);

        updateLabel = new JLabel("");
        add(updateLabel, BorderLayout.PAGE_START);

        int boardSize = selectedBoardSizeSlider.getValue();
        TicTacToeAI.STALEMATE_BIAS = (byte) (10-selectedAiDiffucultySlider.getValue());
        ticTacToeBoard = new TicTacToeBoard(boardSize,boardSize, playerStarting.isSelected(), TicTacToeBoard.X, updateLabel);
        add(ticTacToeBoard, BorderLayout.CENTER);

        gameOverButtons = new JPanel();
        gameOverButtons.setLayout(new GridLayout(1,2));

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e->ticTacToeBoard.resetGame());
        gameOverButtons.add(newGameButton);

        backToMenuButton = new JButton("Back To Main Menu");
        backToMenuButton.addActionListener(this::backToMenu);
        gameOverButtons.add(backToMenuButton);

        add(gameOverButtons, BorderLayout.SOUTH);
    }

    private void backToMenu(ActionEvent e) {
        ticTacToeBoard.setVisible(false);
        gameOverButtons.setVisible(false);
        menuUI.setVisible(true);
    }


    public static void main(String[] args) {
        new MainWindow();
    }

    public void onBoardSizeSliderChange(ChangeEvent e) {
        System.out.println(selectedBoardSizeSlider.getValue());
    }

    public void onAIDifficultySliderChange(ChangeEvent e) {

    }
}