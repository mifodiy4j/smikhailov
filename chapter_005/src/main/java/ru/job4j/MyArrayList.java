package ru.job4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] container;
    private int size = 0;
    int index = 0;

    /**
     * Конструктор
     */
    public MyArrayList() {
        container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавление элемента
     * @param value
     */
    public void add(E value) {
        this.container[index++] = value;
        if(index >= container.length) {
            container = Arrays.copyOf(container, container.length * 3 / 2 + 1);
        }
    }

    /**
     * Взятие элемента по индексу
     * @param index
     * @return
     */
    public E get(int index) {
        if (index >= this.index)
            throw new IndexOutOfBoundsException();

        return (E) this.container[index];
    }

    /**
     * Итератор
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                if (cursor >= index) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                if (cursor >= index) {
                    throw new NoSuchElementException();
                }
                return (E)container[cursor++];
            }
        };
    }
}
