package ru.job4j;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;

/**
 * Realiztion of Stack
 * Created by SERG on 13.10.2017.
 */
public class SimpleStack<E> {

    LinkedList<E> list = new LinkedList<>();
    int index = 0;

    /**
     * Remove last element and return it
     * @return
     */
    public E poll() {
        if (index == 0) {
            throw new EmptyStackException();
        }

        E result = (E)list.getLast();
        list.removeLast();
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
