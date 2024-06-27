package by.zhorau.ipaddrcount.hashmapset;

import java.util.*;

public class HashMapSet implements Set<Integer> {
    private int size =0;
    private final TreeMap<Integer, Set<Integer>> core = new TreeMap<>();
    @Override
    public boolean add(Integer integer) {
        int prevSize = size;
        int leftSide = integer>>>16;
        int rightSide = (integer<<16)>>>16;
        if(core.containsKey(leftSide)){
            if(core.get(leftSide).add(rightSide))
                size++;
        } else {
            core.put(leftSide, new HashSet<>());
            core.get(leftSide).add(rightSide);
            size++;
        }
        return size>prevSize;
    }

    @Override
    public int size() {
        return size;
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

}
