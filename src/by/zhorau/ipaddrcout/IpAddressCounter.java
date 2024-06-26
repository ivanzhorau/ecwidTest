package by.zhorau.ipaddrcout;

import java.util.Iterator;

public class IpAddressCounter {
    private Iterator<String> addressIterator;

    public IpAddressCounter(Iterator<String> addressIterator){
        this.addressIterator = addressIterator;
    }

    public void startCounting(){}

    public long getCount(){
        return 0;
    }
}
