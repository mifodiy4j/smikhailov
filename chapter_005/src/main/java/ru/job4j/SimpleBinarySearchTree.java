package ru.job4j;

public interface SimpleBinarySearchTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param value Value to add.
     * @return
     */
    boolean add(E value);
}
