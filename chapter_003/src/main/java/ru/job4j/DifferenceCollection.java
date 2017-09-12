package ru.job4j;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class DifferenceCollection {
    long deltaTime;

    public static void main(String[] args) {
        List<String> linkedList = new LinkedList<String>();
        List<String> arrayList = new ArrayList<String>();
        Set<String> set = new TreeSet<>();

        DifferenceCollection differenceCollection = new DifferenceCollection();
        deltaTime = differenceCollection.add(linkedList,5);
        System.out.println(deltaTime);
    }

    public long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(new String(i));
        }
        long stop = System.currentTimeMillis();
        return (start - stop);
    }

    public long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.remove();
        }
        long stop = System.currentTimeMillis();
        return (start - stop);
    }

}