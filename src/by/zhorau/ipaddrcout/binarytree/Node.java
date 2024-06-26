package by.zhorau.ipaddrcout.binarytree;

public class Node {
    private final Integer value;
    private Node left;
    private Node right;

    public Node(Integer value){
        this.value = value;
    }

    public Integer getValue() {
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
}
