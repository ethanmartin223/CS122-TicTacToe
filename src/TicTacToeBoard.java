import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TicTacToeBoard extends JPanel {

    // ---------------------------- Constants ---------------------------- //
    public static final byte OPEN_SPACE = 0x0;
    public static final byte X = 0x1;
    public static final byte O = 0x2;

    // ---------------------------- Attributes ---------------------------- //
    private int rows, cols;
    private GameSquare[][] gameBoard;
    private ArrayList<GameSquare> avalibleGameSquares;
    private boolean isPlayersTurn;

    // ---------------------------- Constructors ---------------------------- //
    public TicTacToeBoard(int nCols, int nRows, boolean playerGoesFirst, byte playerMark) {
        gameBoard = new GameSquare[nCols][nRows];
        avalibleGameSquares = new ArrayList<>();

        setLayout(new GridLayout(nCols, nRows));
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

        setVisible(true);

    }

    // ---------------------------- Public Methods ---------------------------- //
    public void clickGameSquare(int x, int y) {
        GameSquare gs = gameBoard[y][x];
        if (isPlayersTurn && !gs.getHasBeenPlayed()) {
            gs.setText("X");
            gs.setMarker(X);
            gs.setEnabled(false);
            avalibleGameSquares.remove(gs);
            setIsPlayersTurn(false);
        }
    }

    public void debugSetBoardState(byte[][] board) {
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < cols; x++) {
                if (board[y][x] != OPEN_SPACE) {
                    gameBoard[y][x].setMarker(board[y][x]);
                    gameBoard[y][x].setText(board[y][x]==X?"X":"O");
                    gameBoard[y][x].setEnabled(false);
                    avalibleGameSquares.remove(gameBoard[y][x]);
                }
            }
    }

    public void resetGame() {
        avalibleGameSquares.clear();
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < cols; x++) {
                avalibleGameSquares.add(gameBoard[y][x]);
                gameBoard[y][x].reset();
            }
        setIsPlayersTurn(true);
    }

    // ---------------------------- Private Methods ---------------------------- //
    private void setIsPlayersTurn(boolean value) {
        //TODO: Change switch to accept player not being X
        switch(detectWin()) {
            case 0 -> { //game is not over
                isPlayersTurn = value;
                setBoardInput(isPlayersTurn);
                if (!isPlayersTurn) {
                    doComputerMove();
                }
                return;
            }

            //X or O has won
            case X -> JOptionPane.showMessageDialog(null, "The Player Wins!");
            case O -> JOptionPane.showMessageDialog(null, "The Computer Wins!");
            //stalemate
            case -1 -> JOptionPane.showMessageDialog(null, "Its A Draw!");
        }
        setBoardInput(false);
    }

    private void setBoardInput(boolean enabled) {
        for (GameSquare g: avalibleGameSquares) {
            g.setEnabled(enabled);
        }
    }

    private byte[][] getByteRepresentationOfBoard() {
        byte[][] result = new byte[gameBoard.length][gameBoard[0].length];
        for (int y = 0; y < gameBoard.length; y++)
            for (int x = 0; x < gameBoard[0].length; x++)
                result[y][x] = gameBoard[y][x].getMarker();
        return result;
    }

    private void doComputerMove() {
        Node move = TicTacToeAI.getBestChild(TicTacToeAI.generateTree(getByteRepresentationOfBoard(), O), O);
        GameSquare selectedTile = gameBoard[move.getMoveY()][move.getMoveX()];
        selectedTile.setText("O");
        selectedTile.setMarker(O);
        selectedTile.setEnabled(false);
        avalibleGameSquares.remove(selectedTile);
        setIsPlayersTurn(true);
    }

    //TODO: remove method duplicate in TicTacToeAI (pass node to static method in class?)
    private byte detectWin() {
        for (int y = 0; y < rows; y++){
            byte startingMarker = gameBoard[y][0].getMarker();
            if (startingMarker == OPEN_SPACE) continue;
            for (int x = 0; x <cols; x++) {
                if (gameBoard[y][x].getMarker() != startingMarker) break;
                if (x>=(cols-1)) return startingMarker;
            }
        }
        for (int x = 0; x < rows; x++){
            byte startingMarker = gameBoard[0][x].getMarker();
            if (startingMarker == OPEN_SPACE) continue;
            for (int y = 0; y <cols; y++) {
                if (gameBoard[y][x].getMarker() != startingMarker) break;
                if (y>=(cols-1)) return startingMarker;
            }
        }
        for (int i=0; i<(rows+cols)/2; i++) {
            byte startingMarker = gameBoard[0][0].getMarker();
            if ((startingMarker == OPEN_SPACE)||(gameBoard[i][i].getMarker() != startingMarker)) break;
            if (i>=((rows+cols)/2)-1) return startingMarker;
        }
        for (int i=cols-1; i>=0; i--) {
            byte startingMarker = gameBoard[0][cols-1].getMarker();
            if ((gameBoard[rows-i-1][i].getMarker() != startingMarker)||(startingMarker == OPEN_SPACE)) break;
            if (i == 0) return startingMarker;
        }
        if (avalibleGameSquares.isEmpty()) return -1;
        return 0;
    }

}
