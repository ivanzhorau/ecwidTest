package by.zhorau.ipaddrcount;

import by.zhorau.ipaddrcount.binarytree.BinaryTree;

import java.nio.ByteBuffer;
import java.util.Iterator;

public class IpAddressCounter {
    private final Iterator<String> addressIterator;
    private final BinaryTree binaryTree = new BinaryTree();
    public IpAddressCounter(Iterator<String> addressIterator){
        this.addressIterator = addressIterator;
    }

    public void startCounting(){
        while(addressIterator.hasNext()){
            String address = addressIterator.next();
            byte[] addressArray = convertAddressToByteArray(address);
            int addressInt = ByteBuffer.wrap(addressArray).getInt();
            binaryTree.add(addressInt);
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
        return binaryTree.size();
    }
}
