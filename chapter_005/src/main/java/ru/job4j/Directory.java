package ru.job4j;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Directory<T, V> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected Object[] catalog;

    public Directory() {
        catalog = new Object[DEFAULT_CAPACITY];
    }

    boolean insert(T key, V value) {
        int k = key.hashCode();
        int index = k % catalog.length;
        catalog[index] = value;

        /*int load = 0;
        for(Object obj : catalog) {
            if (obj != null) {
                load++;
            }
        }

        if (load >= catalog.length * DEFAULT_LOAD_FACTOR) {
            Object[] temp = Arrays.copyOf(catalog, catalog.length);
            catalog = new Object[catalog.length * 3 / 2 + 1];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    catalog
                }
            }
        }*/

        return true;
    }

    V get(T key) {
        int k = key.hashCode();
        int index = k % catalog.length;
        return (catalog[index] != null) ? (V)catalog[index] : null;
    }

    boolean delete(T key) {
        boolean deleteIsGood = false;
        if (get(key) != null) {
            insert(key, null);
            deleteIsGood = true;
        }
        return deleteIsGood;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                for (int i = cursor; i < catalog.length; i++) {
                    if (catalog[i] != null) return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (cursor >= catalog.length) {
                    throw new NoSuchElementException();
                }
                return (T)catalog[cursor++];
            }
        };
    }
}
