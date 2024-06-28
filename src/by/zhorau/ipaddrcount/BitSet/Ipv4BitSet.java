package by.zhorau.ipaddrcount.BitSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Ipv4BitSet implements Set<Integer> {
    private final long[][][][] ips = new long[256][256][256][4];
    private long size = 0;

    public Ipv4BitSet() {
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++)
                    ips[i][j][k] = new long[]{0,0,0,0};
            }
        }
    }

    @Override
    public boolean add(Integer integer) {
        int[] ipArray = convertIntToArray(integer);
        if (setIp(ipArray, true)) {
            size++;
            return true;
        }
        return false;
    }


    @Override
    public int size() {
        return (int) size;
    }

    private int[] convertIntToArray(Integer ip) {
        int[] array = new int[4];
        for (int i = 0; i < 4; i++) {
            array[i] = ((ip << (i * 8)) >>> (24)) + 128;
            array[i] = array[i] % 256;
        }
        return array;
    }

    private long[] convertIntegerToLong(Integer part) {
        long[] array = new long[4];
        for (int i = 0; i < 4; i++) {
            array[i] = 0;
        }
        array[3 - part / 64] = 1L << (part % 64);
        return array;
    }

    private boolean contains(long subSet, long ipPart) {
        return (subSet & ipPart) != 0;
    }

    private boolean setIp(int[] ip, boolean exist) {
        int changes = 0;
        long[] ipl = ips[ip[0]][ip[1]][ip[2]];
        long[] ipLongs = convertIntegerToLong(ip[3]);
        for(int i =0; i<4; i++){
            if (ipLongs[i] != 0) {
                if (exist) {
                    if (!contains(ipl[i], ipLongs[i])) {
                        ipl[i] = ipl[i] | ipLongs[i];
                        changes++;
                    }
                } else {
                    if (contains(ipl[i], ipLongs[i])) {
                        ipl[i] = ipl[i] ^ ipLongs[i];
                        changes++;
                    }
                }
            }
        }

        return changes != 0;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
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
