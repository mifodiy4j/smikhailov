package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class MyArrayList<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 10;
    protected Object[] container;
    int size = 0;
    protected int index = 0;

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
    public synchronized void add(E value) {
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
    public synchronized E get(int index) {
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
                return (cursor < index);
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
