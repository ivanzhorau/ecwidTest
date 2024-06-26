package by.zhorau.ipaddrcout;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IpAddressCounter {
    private final Iterator<String> addressIterator;
    private Set<Integer> set = new HashSet<>();
    public IpAddressCounter(Iterator<String> addressIterator){
        this.addressIterator = addressIterator;
    }

    public void startCounting(){
        while(addressIterator.hasNext()){
            String address = addressIterator.next();
            byte[] addressArray = convertAddressToByteArray(address);
            int addressInt = ByteBuffer.wrap(addressArray).getInt();
            System.out.println(addressInt);
            //bTree.add(addressInt);
            set.add(addressInt);
        }
    }

    private byte[] convertAddressToByteArray(String address) {
        byte[] array = new byte[4];
        int index = 0;
        for(String part : address.split("\\.")){
            array[index] = (byte)(Integer.parseInt(part) - Byte.MAX_VALUE -1);
            index++;
        }
        return array;
    }

    public long getCount(){
        //return bTree.size();
        return set.size();
    }
}
