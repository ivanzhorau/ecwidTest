package by.zhorau.ipaddrcount;

import by.zhorau.ipaddrcount.BitSet.Ipv4BitSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("res/ip_addresses");
//        File file = new File("res/address.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        IpAddressCounter counter = new IpAddressCounter(scanner, new HashSet<>());
//        IpAddressCounter counter = new IpAddressCounter(scanner, new BinaryTree());
        IpAddressCounter counter = new IpAddressCounter(scanner, new Ipv4BitSet());
        Date startTime = new Date();
        System.out.println(startTime);
        counter.startCounting();
        System.out.println(counter.getCount());
        Date endTime = new Date();
        System.out.println(endTime);
        System.out.println(endTime.getTime()-startTime.getTime());

    }
}
