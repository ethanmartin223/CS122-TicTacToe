import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;
import java.awt.*;
import java.util.ArrayList;

public class TicTacToeBoard extends JPanel {
    public static byte OPEN_SPACE = 0x0;
    public static byte X = 0x1;
    public static byte O = 0x2;

    private int rows, cols;
    private GameSquare[][] gameBoard;
    private ArrayList<GameSquare> avalibleGameSquares;
    private boolean isPlayersTurn;

    public TicTacToeBoard(int rows, int cols) {
        this(rows, cols, true);
    }

    public TicTacToeBoard(int nCols, int nRows, boolean playerGoesFirst) {
        gameBoard = new GameSquare[nCols][nRows];
        avalibleGameSquares = new ArrayList<>();

        setLayout(new GridLayout(nCols, nRows));
        setVisible(true);
        rows = nRows;
        cols = nCols;
        isPlayersTurn = playerGoesFirst;

        for (int y = 0; y < nCols; y++) {
            for (int x = 0; x < nRows; x++) {
                gameBoard[y][x] = new GameSquare(x,y,this);
                avalibleGameSquares.add(gameBoard[y][x]);
                add(gameBoard[y][x]);
            }
        }

    }

    private void setIsPlayersTurn(boolean value) {
        //check for win
        byte player = detectWin();
        System.out.println(player);
        if (player != 0) {
            System.out.println("Game Over");
            if  (player==X) {
                JOptionPane.showMessageDialog(null, "The Player Wins!");
            }
            else {
                JOptionPane.showMessageDialog(null, "The Computer Wins");
            }
            setBoardInput(false);
            return;
        }


        isPlayersTurn = value;
        setBoardInput(isPlayersTurn);
        if (!isPlayersTurn) {
            doComputerMove();
        }
    }

    private void setBoardInput(boolean enabled) {
        for (GameSquare g: avalibleGameSquares) {
            g.setEnabled(enabled);
        }
    }

    private void doComputerMove() {
        System.out.println("computerMoveFired");
        GameSquare selectedTile = avalibleGameSquares.get((int) (Math.random() * avalibleGameSquares.size()));
        selectedTile.setText("O");
        selectedTile.setMarker(O);
        selectedTile.setEnabled(false);
        avalibleGameSquares.remove(selectedTile);
        setIsPlayersTurn(true);
    }

    public void clickGameSquare(int x, int y) {
        GameSquare gs = gameBoard[y][x];
        if (isPlayersTurn && !gs.hasBeenPlayed()) {
            gs.setText("X");
            gs.setMarker(X);
            gs.setEnabled(false);
            avalibleGameSquares.remove(gs);
            setIsPlayersTurn(false);
        }
    }

    public byte getMarkAt(int x, int y) {
        return gameBoard[y][x].getMarker();
    }

    //return marker of winner, else return 0
    private byte detectWin() {
        //check horizontal
        for (int y = 0; y < rows; y++){
            byte startingMarker = gameBoard[y][0].getMarker();
            if (startingMarker == OPEN_SPACE) continue;
            for (int x = 0; x <cols; x++) {
                if (gameBoard[y][x].getMarker() != startingMarker) break;
                if (x>=(cols-1)) {
                    return startingMarker;
                }
            }
        }
        for (int x = 0; x < rows; x++){
            byte startingMarker = gameBoard[0][x].getMarker();
            if (startingMarker == OPEN_SPACE) continue;
            for (int y = 0; y <cols; y++) {
                if (gameBoard[y][x].getMarker() != startingMarker) break;
                if (y>=(cols-1)) {
                    return startingMarker;
                }
            }
        }


        if (avalibleGameSquares.isEmpty()) return 0;
        return 0;
    }

}
