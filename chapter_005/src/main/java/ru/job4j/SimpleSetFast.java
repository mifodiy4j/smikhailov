package ru.job4j;

import java.util.Arrays;

/**
 * Реализация коллекции Set на массиве
 * Created by SERG on 17.10.2017.
 */
public class SimpleSetFast<E> extends MyArrayList<E> {

    /**
     * Конструтор
     */
    public SimpleSetFast() {
        super();
    }

    /**
     * @param e
     * @return
     */
    /*public boolean contains(E e) {
        boolean findElement = false;
        for (int i = 0; i < index; i++) {
            if (container[i].equals(e)) {
                findElement = true;
                break;
            }
        }
        return findElement;
    }*/

    public boolean contains(E e) {
        if (index == 0) return false;
        int last = index - 1;
        int first = 0;
        int mid = (last + 1) / 2;
        while (index != 0 && container[mid].hashCode() != e.hashCode() && last != mid && first != mid) {
            if (container[mid].hashCode() < e.hashCode()) {
                first = mid;
                mid = (last + first) / 2;
            } else {
                last = mid;
                mid = (last + first) / 2;
            }
        }
        if (container[last].hashCode() == e.hashCode())
            mid = last;
        if (container[first].hashCode() == e.hashCode())
            mid = first;
        return container[mid] == e;
    }

    /**
     * Добавление элемента
     * @param e
     */
    @Override
    public void add(E e) {

        if (!contains(e)) {
            container[index++] = e;
            if(index >= container.length) {
                container = Arrays.copyOf(container, container.length * 3 / 2 + 1);
            }
            sortByHashCode();
        }
    }

    private void sortByHashCode() {
        for (int i = index - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (container[j].hashCode() < container[j + 1].hashCode()) {
                    Object temp = container[j + 1];
                    container[j + 1] = container[j];
                    container[j] = temp;
                }
            }
        }
    }

}
