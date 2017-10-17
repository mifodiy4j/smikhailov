package ru.job4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация коллекции Set на массиве
 * Created by SERG on 17.10.2017.
 */
public class SimpleSet<E> implements Iterable<E> {
    Object[] objects;
    int position = 0;

    /**
     * Конструтор
     * @param size
     */
    public SimpleSet(int size) {
        this.objects = new Object[size];
    }

    /**
     * Добавление элемента
     * @param e
     */
    public void add(E e) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == e) {
                return;
            }
        }
        objects[position++] = e;
        if(position >= objects.length) {
            objects = Arrays.copyOf(objects, objects.length * 3 / 2 + 1);
        }
    }


    /**
     * Итератор
     * @return Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                if (cursor >= position) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                if (cursor >= position) {
                    throw new NoSuchElementException();
                }
                return (E)objects[cursor++];
            }
        };
    }
}
