package ru.job4j;

import java.util.*;

public class DifferenceCollection {

    public static void main(String[] args) {
        List<String> linkedList = new LinkedList<String>();
        List<String> arrayList = new ArrayList<String>();
        Set<String> set = new TreeSet<>();

        int numAdd = 1000_000;
        int numDel = 10_000;

        DifferenceCollection differenceCollection = new DifferenceCollection();

        long deltaTime = differenceCollection.add(linkedList, numAdd);
        System.out.println(String.format("linkedList time to add %d : %d ms", numAdd ,deltaTime));

        deltaTime = differenceCollection.add(arrayList, numAdd);
        System.out.println(String.format("arrayList time to add %d : %d ms", numAdd ,deltaTime));

        deltaTime = differenceCollection.add(set, numAdd);
        System.out.println(String.format("set time to add %d : %d ms", numAdd ,deltaTime));

        deltaTime = differenceCollection.delete(linkedList, numDel);
        System.out.println(String.format("linkedList time to remove %d : %d ms", numDel ,deltaTime));

        deltaTime = differenceCollection.delete(arrayList, numDel);
        System.out.println(String.format("arrayList time to remove %d : %d ms", numDel ,deltaTime));

        deltaTime = differenceCollection.delete(set, numDel);
        System.out.println(String.format("set time to remove %d : %d ms", numDel ,deltaTime));
    }

    public long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(String.valueOf(i));
        }
        long stop = System.currentTimeMillis();
        return (stop - start);
    }

    public long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.remove(String.valueOf(i));
        }
        long stop = System.currentTimeMillis();
        return (stop - start);
    }

}