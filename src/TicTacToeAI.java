import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class TicTacToeAI {

    // ---------------------------- AI Settings ---------------------------- //
    private static final int WINNING_BIAS = 10;
    private static final int LOSING_BIAS = -10;
    private static final int STALEMATE_BIAS = 0;
    private static final int CENTER_BIAS = 5;

    // ---------------------------- Constructors ---------------------------- //
    private TicTacToeAI() {}

    // ---------------------------- Public Methods ---------------------------- //
    public static Node generateTree(byte[][] data, byte playerTurn) {
        ArrayList<Node> instances = new ArrayList<>();
        return generateTree(data, new Node(data, playerTurn), playerTurn, instances);
    }

    public static Node getBestChild(Node n, byte playerMaxing) {
        ArrayList<Node> children = n.getChildren();
        Node bestNode = null;
        int bestScore = Integer.MIN_VALUE;
        int currentScore;
        for (Node child: children) {
            currentScore = minMax(child, playerMaxing);
            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestNode = child;
            }
        }
        return bestNode;
    }

    public static int evaluateNode(Node node, byte playerTurn) {
        int winner = checkForWin(node);
        int gameSquareScore = 0;
        switch (winner) {
            case 1, 2 -> { //winner found
                gameSquareScore+= winner==playerTurn?WINNING_BIAS-(node.getDepth()):LOSING_BIAS+(node.getDepth());
            }
            case -1 -> {gameSquareScore += STALEMATE_BIAS;} //draw
        }
        //TODO: remove later
        if (node.getMoveX() == 1 && node.getMoveY() == 1) gameSquareScore+=CENTER_BIAS;
        return gameSquareScore;
    }

    // ---------------------------- Private Methods ---------------------------- //
    private static Node generateTree(byte[][] boardData, Node parent, byte playerTurn,
                                     ArrayList<Node> instances) {
        for (int y = 0; y < boardData.length; y++) {
            for (int x=0; x<boardData[0].length; x++) {
                if (boardData[y][x]==0) {
                    byte[][] boardCopy = deepCopy(boardData);
                    boardCopy[y][x] = playerTurn;
                    Node child = new Node(parent, boardCopy, (byte)(playerTurn==1?2:1), (byte)x,(byte)y);
                    // Tie preexisting nodes to parent
                    boolean foundNodeInTree = false;
                    for (Node n : instances)
                        if ((n.nodeEquals(child))) {
                            foundNodeInTree = true;
                            parent.addChild(n);
                            break;
                        }
                    if (foundNodeInTree) continue; //if we found a duplicate node then tie and forget
                    else instances.add(child); // else add new node
                    parent.addChild(child);

                    int total = evaluateNode(child, (byte)(playerTurn==1?2:1));
                    child.setScore(total);
                    if (total != Integer.MAX_VALUE && total != Integer.MIN_VALUE)
                        generateTree(boardCopy, child, (byte)(playerTurn==1?2:1), instances);
                }
            }
        }
        return parent;
    }

    private static int minMax(Node n, byte playerMaxing) {
        if (n.getChildren().isEmpty()) return n.getScore();
        int score;
        if (playerMaxing==n.getPlayerMoved()) {
            score = Integer.MIN_VALUE;
            for (Node child : n.getChildren())
                score = n.getScore()+max(score, minMax(child, playerMaxing));
        } else {
            score = Integer.MAX_VALUE;
            for (Node child : n.getChildren())
                score = n.getScore()+min(score, minMax(child, playerMaxing));
        }
        return score;
    }

    private static int checkForWin(Node node) {
        int rows=node.getValue().length, cols=node.getValue()[0].length;
        byte[][] gameBoard = node.getValue();
        //check horizontal
        for (int y = 0; y < rows; y++){
            byte startingMarker = gameBoard[y][0];
            if (startingMarker == 0) continue;
            for (int x = 0; x <cols; x++) {
                if (gameBoard[y][x] != startingMarker) break;
                if (x>=(cols-1)) return startingMarker;
            }
        }
        //check Vertical
        for (int x = 0; x < rows; x++){
            byte startingMarker = gameBoard[0][x];
            if (startingMarker == 0) continue;
            for (int y = 0; y <cols; y++) {
                if (gameBoard[y][x] != startingMarker) break;
                if (y>=(cols-1)) return startingMarker;
            }
        }
        //check diagonal TL-BR
        for (int i=0; i<(rows+cols)/2; i++) {
            byte startingMarker = gameBoard[0][0];
            if ((startingMarker == 0)||(gameBoard[i][i] != startingMarker)) break;
            if (i>=((rows+cols)/2)-1) return startingMarker;
        }
        //check diagonal TR-BL
        for (int i=cols-1; i>=0; i--) {
            byte startingMarker = gameBoard[0][cols-1];
            if ((gameBoard[rows-i-1][i] != startingMarker)||(startingMarker == 0)) break;
            if (i<=0) return startingMarker;
        }

        //check for draw
        int spotsFilled = 0;
        for (int y = 0; y < rows; y++)
            for (int x = 0; x <cols; x++)
                if (gameBoard[y][x] != 0) spotsFilled++;
        if (spotsFilled >= rows*cols) return -1;
        return 0;
    }

    private static byte[][] deepCopy(byte[][] data) {
        byte[][] copy = new byte[data.length][data[0].length];
        for (int y = 0; y < data.length; y++)
            copy[y] = Arrays.copyOf(data[y], data[y].length);
        return copy;
    }

}
