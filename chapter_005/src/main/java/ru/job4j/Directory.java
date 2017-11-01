package ru.job4j;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Directory<T, V> implements Iterable {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected Node[] catalog = new Node[DEFAULT_CAPACITY];


    boolean insert(T key, V value) {
        int k = key.hashCode();
        int index = k % catalog.length;
        catalog[index] = new Node<>(key, value);

        int load = 0;
        for(Node node : catalog) {
            if (node != null) {
                load++;
            }
        }

        if (load >= catalog.length * DEFAULT_LOAD_FACTOR) {
            Node[] temp = Arrays.copyOf(catalog, catalog.length);
            catalog = new Node[catalog.length * 3 / 2 + 1];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    key = (T) temp[i].getKey();
                    k = key.hashCode();
                    index = k % catalog.length;
                    value = (V) temp[i].getValue();
                    catalog[index] = new Node<>(key, value);
                }
            }
        }

        return true;
    }

    V get(T key) {
        int k = key.hashCode();
        int index = k % catalog.length;
        return (catalog[index] != null) ? (V) catalog[index].getValue() : null;
    }

    boolean delete(T key) {
        int k = key.hashCode();
        int index = k % catalog.length;
        catalog[index] = null;
        return true;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                for (int i = cursor; i < catalog.length; i++) {
                    if (catalog[i] != null) return true;
                }
                return false;
            }

            @Override
            public Node next() {
                if (cursor >= catalog.length) {
                    throw new NoSuchElementException();
                }
                int prevCursor = cursor;
                hasNext();
                return catalog[prevCursor];
            }
        };
    }

    private class Node<T, V> {
        T key;
        V value;

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public T getKey() {
            return key;
        }
    }
}
