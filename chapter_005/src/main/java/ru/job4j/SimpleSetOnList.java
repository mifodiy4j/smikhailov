package ru.job4j;

/**
 * Реализация коллекции Set на связном списке
 * @param <E>
 */
public class SimpleSetOnList<E> extends MyLinkedList<E> {

    /**
     * Проверка наличия данного элемента в коллекции
     * @param e
     * @return true if the collection has the element e
     */
    public boolean contains(E e) {
        boolean findElement = false;

        int index = 0;
        if (e == null) {
            for (Element<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    findElement = true;
                    break;
                }
                index++;
            }
        } else {
            for (Element<E> x = first; x != null; x = x.next) {
                if (e.equals(x.item)) {
                    findElement = true;
                    break;
                }
                index++;
            }
        }
        return findElement;
    }

    /**
     * Добавление элемента в коллекцию, если элемента еще нет в коллекции
     * @param e
     * @return true if element e add the collection
     */
    @Override
    public boolean add(E e) {
        boolean markerGoodAdd;
        if (contains(e)) {
            markerGoodAdd = false;
        } else {
            super.add(e);
            markerGoodAdd = true;
        }
        return markerGoodAdd;
    }

}
