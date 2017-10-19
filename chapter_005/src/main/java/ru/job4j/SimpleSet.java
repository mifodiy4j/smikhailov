package ru.job4j;

import java.util.Arrays;

/**
 * Реализация коллекции Set на массиве
 * Created by SERG on 17.10.2017.
 */
public class SimpleSet<E> extends MyArrayList<E> {
    //Object[] objects;
    //public Object[] container;
    //int index = 0;

    /**
     * Конструтор
     */
    public SimpleSet() {
        super();
    }

    public boolean contains(E e) {
        boolean findElement = false;
        for (int i = 0; i < container.length; i++) {
            if (container[i] == e) {
                findElement = true;
                break;
            }
        }
        return findElement;
    }

    /**
     * Добавление элемента
     * @param e
     */
    @Override
    public void add(E e) {

        if (!contains(e)) {
            container[index++] = e;
            if (index >= container.length) {
                container = Arrays.copyOf(container, container.length * 3 / 2 + 1);
            }
        }
    }

}
