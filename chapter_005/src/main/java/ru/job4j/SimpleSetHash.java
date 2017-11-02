package ru.job4j;

import java.util.Arrays;

public class SimpleSetHash<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected Object[] container;

    /**
     * Конструтор
     */
    public SimpleSetHash() {
        container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Добавление элемента
     * @param e
     * @return true после добавления нового элемента, false если данный элемент уже существует
     */
    public boolean add(E e) {

        if (!contains(e)) {
            int k = e.hashCode();
            int index = k % container.length;
            container[index] = e;
            if(index >= container.length) {
                container = Arrays.copyOf(container, container.length * 3 / 2 + 1);
            }
        } else {
            return false;
        }

        int load = 0;
        for (Object obj : container) {
            if (obj != null) {
                load++;
            }
        }

        if (load >= container.length * DEFAULT_LOAD_FACTOR) {
            Object[] temp = Arrays.copyOf(container, container.length);
            container = new Object[container.length * 3 / 2 + 1];
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    int k = temp[i].hashCode();
                    int index = k % container.length;
                    container[index] = temp[i];
                }
            }
        }
        return true;
    }

    /**
     * Проверка на наличие элемента в коллекции
     * @param e
     * @return true если элемент уже есть в коллекции, false если данного элемента нет в коллекции
     */
    public boolean contains(E e) {
        int k = e.hashCode();
        int index = k % container.length;
        return container[index] != null;
    }

    /**
     * Удаление элемента
     * @param e
     * @return true после удаления элемента
     */
    public boolean remove(E e) {
        int k = e.hashCode();
        int index = k % container.length;
        container[index] = null;
        return true;
    }
}
