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
        }
        Node current = root;
        while (value != current.getValue()) {
            if (value < current.getValue()) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node(value, current));
                    size++;
                    current = current.getLeft();
                    fixAfterInsertion(current);
                    break;
                } else {
                    current = current.getLeft();
                }
            }
            if (value > current.getValue()) {
                if (current.getRight() == null) {
                    current.setRight(new Node(value, current));
                    size++;
                    current = current.getRight();
                    fixAfterInsertion(current);
                    break;
                } else {
                    current = current.getRight();
                }
            }
        }
    }

    private void rotateLeft(Node p) {
        if (p != null) {
            Node r = p.getRight();
            p.setRight(r.getLeft());
            if (r.getLeft() != null)
                r.getLeft().setParent(p);
            r.setParent(p.getParent());
            if (p.getParent() == null)
                root = r;
            else if (p.getParent().getLeft() == p)
                p.getParent().setLeft(r);
            else
                p.getParent().setRight(r);
            r.setLeft(p);
            p.setParent(r);
        }
    }

    /** From CLR */
    private void rotateRight(Node p) {
        if (p != null) {
            Node l = p.getLeft();
            p.setLeft(l.getRight());
            if (l.getRight() != null) l.getRight().setParent(p);
            l.setParent(p.getParent());
            if (p.getParent() == null)
                root = l;
            else if (p.getParent().getRight() == p)
                p.getParent().setRight(l);
            else p.getParent().setLeft(l);
            l.setRight(p);
            p.setParent(l);
        }
    }

    /** From CLR */
    private void fixAfterInsertion(Node x) {
        x.setColor(Node.RED);

        while (x != null && x != root && x.getParent().getColor() == Node.RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Node y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == Node.RED) {
                    setColor(parentOf(x), Node.BLACK);
                    setColor(y, Node.BLACK);
                    setColor(parentOf(parentOf(x)), Node.RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), Node.BLACK);
                    setColor(parentOf(parentOf(x)),Node. RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Node y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == Node.RED) {
                    setColor(parentOf(x), Node.BLACK);
                    setColor(y, Node.BLACK);
                    setColor(parentOf(parentOf(x)), Node.RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), Node.BLACK);
                    setColor(parentOf(parentOf(x)), Node.RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.setColor(Node.BLACK);
    }
    private static boolean colorOf(Node p) {
        return (p == null ? Node.BLACK : p.getColor());
    }

    private static Node parentOf(Node p) {
        return (p == null ? null: p.getParent());
    }

    private static void setColor(Node p, boolean c) {
        if (p != null)
            p.setColor(c);
    }

    private static Node leftOf(Node p) {
        return (p == null) ? null: p.getLeft();
    }

    private static Node rightOf(Node p) {
        return (p == null) ? null: p.getRight();
    }
    public long size() {
        return size;
    }
}
