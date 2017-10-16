package ru.job4j;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Realization of Queue
 * Created by SERG on 16.10.2017.
 */
public class SimpleQueue<E> {

    LinkedList<E> list = new LinkedList<>();
    int index = 0;

    /**
     * Remove first element and return it
     * @return
     */
    public E poll() {
        if (index == 0) {
            throw new NoSuchElementException();
        }

        E result = (E)list.getFirst();
        list.removeFirst();
        index--;

        return result;
    }

    /**
     * Add element in last on the collection
     * @param value
     */
    public void push(E value) {
        list.addLast(value);
        index++;
    }
}
