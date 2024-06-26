package by.zhorau.ipaddrcount.binarytree;

public class BinaryTree {
    private long size = 0;
    private Node root;

    public BinaryTree() {
    }

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return;
        }
        Node current = root;
        while (value != current.getValue()) {
            if (value < current.getValue()) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node(value));
                    size++;
                    break;
                } else {
                    current = current.getLeft();
                }
            }
            if (value > current.getValue()){
                if(current.getRight() == null){
                    current.setRight(new Node(value));
                    size++;
                    break;
                } else {
                    current = current.getRight();
                }
            }
        }

    }

    public long size() {
        return size;
    }
}
