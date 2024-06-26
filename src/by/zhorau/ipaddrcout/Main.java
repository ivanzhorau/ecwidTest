package by.zhorau.ipaddrcout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        File file = new File("res/address.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        IpAddressCounter counter = new IpAddressCounter(scanner);
        counter.startCounting();
        System.out.println(counter.getCount());//10
        file = new File("res/address.txt");
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Set<String> set = new HashSet<>();
        while (scanner.hasNext()){
            set.add(scanner.next());
        }
        System.out.println(set.size());
    }
}
