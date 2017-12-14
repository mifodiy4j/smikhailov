package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class MyArrayList<E> implements Iterable<E> {

    private Object lock = new Object();

    private static final int DEFAULT_CAPACITY = 10;

    @GuardedBy("lock")
    protected Object[] container;

    @GuardedBy("lock")
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
    public void add(E value) {
        synchronized(lock) {
            this.container[index++] = value;
            if (index >= container.length) {
                container = Arrays.copyOf(container, container.length * 3 / 2 + 1);
            }
        }
    }

    /**
     * Взятие элемента по индексу
     * @param index
     * @return
     */
    public E get(int index) {
        synchronized(lock) {
            if (index >= this.index)
                throw new IndexOutOfBoundsException();

            return (E) this.container[index];
        }
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
