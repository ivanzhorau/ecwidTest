package by.zhorau.ipaddrcount;

import java.util.Iterator;
import java.util.Set;

public class IpAddressCounter {
    private final Iterator<String> addressIterator;
    private final Set<Integer> set;

    public IpAddressCounter(Iterator<String> addressIterator, Set<Integer> set) {
        this.addressIterator = addressIterator;
        this.set = set;
    }

    public void startCounting() {
        long iterations = 0;
        while (addressIterator.hasNext()) {
            String address = addressIterator.next();
            short[] parsedAddress = convertAddressToShortArray(address);
            if (!isValid(parsedAddress)) {
                System.out.printf("Invalid address %s at %d line will be skipped\n", address, iterations);
                continue;
            }
            int addressInt = convertAddressToInteger(parsedAddress);
            set.add(addressInt);
            iterations++;
            if (iterations % 10000000 == 0) {
                System.out.println("Checked " + iterations + " lines\n" + "Count " + getCount() + "\n");
            }
        }
    }

    private short[] convertAddressToShortArray(String address) {
        short[] array = new short[4];
        byte index = 0;
        for (String part : address.split("\\.")) {
            array[index] = Short.parseShort(part);
            index++;
        }
        return array;
    }

    private boolean isValid(short[] address) {
        for (short part : address) {
            if (part > 255) return false;
        }
        return true;
    }

    private int convertAddressToInteger(short[] address) {
        int addressInt = 0;
        int partInt;
        byte index = 0;
        for (short part : address) {
            partInt = (byte)(part - Byte.MAX_VALUE - 1)<<24;
            partInt = partInt>>>(8*index);
            addressInt = addressInt | partInt;
            index++;
        }
        return addressInt;
    }

    public long getCount() {
        return set.size();
    }
}
