package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by SERG on 13.10.2017.
 */
public class MyLinkedList<E> implements Iterable<E> {
    protected int size = 0;

    protected Element<E> first;
    protected Element<E> last;

    /**
     * Элемент коллекции
     * @param <E>
     */
    protected static class Element<E> {
        E item;
        Element<E> next;
        Element<E> prev;

        Element(Element<E> prev, E item, Element<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Добавление элемента
     * @param value
     * @return true after add new element at the list
     */
    public boolean add(E value) {
        Element<E> l = last;
        Element<E> newNode = new Element<E>(l, value, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;

        size++;

        return true;
    }

    /**
     * Взятие элемента по индексу
     * @param index
     * @return the element at the position
     */
    public E get(int index) {
        if (index >= 0 && index < size) {
            Element<E> x;
            if (index < (size >> 1)) {
                x = first;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
            } else {
                x = last;
                for (int i = size - 1; i > index; i--) {
                    x = x.prev;
                }
            }
            return x.item;

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Итератор
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Element<E> x = first;

            @Override
            public boolean hasNext() {
                if (x == null) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                if (x == null) {
                    throw new NoSuchElementException();
                }

                Element<E> res = x;
                x = x.next;
                return res.item;
            }
        };
    }
}

