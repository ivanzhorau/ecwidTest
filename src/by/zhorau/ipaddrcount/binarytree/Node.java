package by.zhorau.ipaddrcount.binarytree;

public class Node {
    public static final boolean RED   = false;
    public static final boolean BLACK = true;
    private final int value;
    private Node left;
    private Node right;
    private Node parent;
    private boolean color = BLACK;

    public Node(int value){
        this.value = value;
    }
    public Node(int value, Node parent){
        this.value = value;
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
