import java.util.ArrayList;
import java.util.Arrays;

public class Node {

    // ---------------------------- Attributes ---------------------------- //
    private final Node parent;
    private ArrayList<Node> children;
    private byte[][] value;
    private int score;
    private int depth;
    private byte playerMoved;
    private byte moveX, moveY;

    // ---------------------------- Constructors ---------------------------- //
    public Node(Node parent, byte[][] v, byte pm, byte x, byte y) {
        this.parent = parent;
        if (parent!=null) {
            this.depth = parent.getDepth() + 1;
        } else depth = 0;
        children = new ArrayList<>();
        value = v;
        score = 0;
        playerMoved = pm;
        moveX =x;
        moveY =y;
    }

    public Node(byte[][] v, byte pm) {
        this(null, v, pm, (byte)-1, (byte)-1); //-1,-1 used as placeholder
    }

    // ---------------------------- Public Methods ---------------------------- //
    public boolean nodeEquals(Node other) {
        return Arrays.deepEquals(other.value, this.value);
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void setScore(int s) {
        score = s;
    }

    public ArrayList<Node> getChildren() {return children;}
    public byte[][] getValue() {return value;}
    public int getScore() {return score;}
    public int getDepth() {return depth;}
    public byte getPlayerMoved() {return playerMoved;}
    public byte getMoveX() {return moveX;}
    public byte getMoveY() {return moveY;}

}
