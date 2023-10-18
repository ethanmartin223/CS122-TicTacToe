import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameSquare extends JButton {

    // ---------------------------- Attributes ---------------------------- //
    private final int x, y;
    private boolean hasBeenPlayed;
    private final TicTacToeBoard parentBoard;
    private byte marker;

    // ---------------------------- Constructors ---------------------------- //
    public GameSquare(int x, int y, TicTacToeBoard gameBoard) {
        hasBeenPlayed = false;
        parentBoard = gameBoard;
        this.x = x;
        this.y = y;
        this.marker = TicTacToeBoard.OPEN_SPACE;
        setBackground(Color.WHITE);
        setFocusPainted(false);

        addActionListener(this::onClick);
        setFont(new Font("Arial", Font.BOLD, 180-(gameBoard.getBoardSize()*20)));
        setVisible(true);
    }

    // ---------------------------- Public Methods ---------------------------- //
    public void setMarker(byte marker) {
        this.marker = marker;
    }

    public void reset() {
        hasBeenPlayed = false;
        this.marker = TicTacToeBoard.OPEN_SPACE;
        setText("");
    }

    public byte getMarker() {return marker;}
    public boolean getHasBeenPlayed() {return hasBeenPlayed;}

    // ---------------------------- Private Methods ---------------------------- //

    private void onClick(ActionEvent event) {
       parentBoard.clickGameSquare(x,y);
       hasBeenPlayed = true;
    }
}
