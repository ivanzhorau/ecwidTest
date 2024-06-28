package by.zhorau.ipaddrcount.binarytree;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class BinaryTree implements Set<Integer> {
    private long size = 0;
    private Node root;

    public BinaryTree() {
    }

    public boolean add(Integer value) {
        if (root == null) {
            root = new Node(value);
            size++;
        }
        Node current = root;
        int currentValue;
        while (value != (currentValue = current.getValue())) {
            if (value < currentValue) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node(value, current));
                    size++;
                    current = current.getLeft();
                    fixAfterInsertion(current);
                    return true;
                } else {
                    current = current.getLeft();
                }
            }
            if (value > currentValue) {
                if (current.getRight() == null) {
                    current.setRight(new Node(value, current));
                    size++;
                    current = current.getRight();
                    fixAfterInsertion(current);
                    return true;
                } else {
                    current = current.getRight();
                }
            }
        }
        return false;
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
    public int size() {
        return (int)size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
