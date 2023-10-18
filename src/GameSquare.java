import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        //addMouseListener(new HoverListner(this));
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

    public void setPlayed() {
        hasBeenPlayed = true;
    }

    public byte getMarker() {return marker;}
    public boolean getHasBeenPlayed() {return hasBeenPlayed;}

    // ---------------------------- Private Methods ---------------------------- //

    private void onClick(ActionEvent event) {
       parentBoard.clickGameSquare(x,y);
       hasBeenPlayed = true;
    }

    // ---------------------------- Internal Classes ---------------------------- //
    private class HoverListner extends MouseAdapter {
        GameSquare gameSquare;

        public HoverListner(GameSquare g) {

            gameSquare = g;
        }

        public void mouseEntered(MouseEvent e) {
            if (!gameSquare.getHasBeenPlayed()) gameSquare.setText(parentBoard.getPlayerMark()==TicTacToeBoard.X?"X":"O");
        }

        public void mouseExited(MouseEvent e) {
            gameSquare.setText(gameSquare.getMarker()==0?"":gameSquare.getMarker()==TicTacToeBoard.X?"X":"O");
        }
    }
}
