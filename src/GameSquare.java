import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GameSquare extends JButton {

    private int x, y;
    private boolean hasBeenPlayed;
    private final TicTacToeBoard parentBoard;
    private byte marker;

    public GameSquare(int x, int y, TicTacToeBoard gameBoard) {
        hasBeenPlayed = false;
        parentBoard = gameBoard;
        this.x = x;
        this.y = y;
        this.marker = TicTacToeBoard.OPEN_SPACE;
        setBackground(Color.WHITE);

        addActionListener(this::onClick);
        setFont(new Font("Arial", Font.BOLD, 120));
        setVisible(true);
    }

    private void onClick(ActionEvent event) {
       parentBoard.clickGameSquare(x,y);
       hasBeenPlayed = true;
    }

    public byte getMarker() {
        return marker;
    }

    public void setMarker(byte marker) {
        this.marker = marker;
    }

    public boolean hasBeenPlayed() {return hasBeenPlayed;}


}
