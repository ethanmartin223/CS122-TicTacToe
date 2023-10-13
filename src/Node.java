import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private final Node parent;
    private ArrayList<Node> children;
    private byte[][] value;
    private int score;
    private int depth;
    private byte playerMoved;
    private byte moveX, moveY;

    public void printTree(boolean printLines) {
//        System.out.println(score);
//        if (!this.children.isEmpty()) {
//            for (int x=0; x<children.size(); x++) {
//                for (int i = 0; i < children.get(x).depth; i++)
//                    System.out.print(printLines?"|   ":"    ");
//                if (x == children.size() - 1) {
//                    System.out.print("└── ");
//                    children.get(x).printTree(false);
//                } else {
//                    System.out.print("├── ");
//                    children.get(x).printTree(true);
//                }
//            }
//        }
    }

    public Node(Node parent, byte[][] v, byte pm, byte x, byte y) {
        this.parent = parent;
        if (parent!=null) {
            this.depth = parent.getDepth() + 1;
        } else depth = 1;
        children = new ArrayList<>();
        value = v;
        score = 0;
        playerMoved = pm;
        moveX =x;
        moveY =y;
    }

    public Node(byte[][] v, byte pm) {
        this(null, v, pm, (byte)-1, (byte)-1);
    }

    public String toString() {
        return this.getClass().getName()+ value.toString();
    }

    public boolean nodeEquals(Node other) {
        return Arrays.deepEquals(other.value, this.value);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public void addChild(Node child) {
        children.add(child);
    }


    public byte[][] getValue() {
        return value;
    }

    public void setScore(int s) {
        score = s;
    }

    public int getScore() {
        return score;
    }

    public int getDepth() {
        return depth;
    }

    public byte getPlayerMoved() {
        return playerMoved;
    }

    public byte getMoveX() {
        return moveX;
    }

    public byte getMoveY() {
        return moveY;
    }
}
