package ru.job4j;

import java.util.Arrays;

/**
 * @param <E>
 */
public class SimpleArray<E> {

    Object[] objects;
    int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Добавляет новый элемент <E> в массив по текущему индексу.
     * Индекс увеличивается на единицу.
     * @param value
     */
    public void add(E value) {
        this.objects[index++] = value;
        if(index >= objects.length) {
            objects = Arrays.copyOf(objects, objects.length * 3 / 2 + 1);
        }
    }

    /**
     * Записывает новый элемент <E> в массив по указанному индексу
     * @param position
     * @param newValue
     */
    public void update(int position, E newValue) {
        this.objects[position] = newValue;
    }

    /**
     * Удаляет элемент по текущему индексу, элементы стоящие справа сдвигаются
     * на один элемент влево
     * @param position
     */
    public void delete(int position) {
        for (int i = position; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        if (index >= position) {
            index--;
        }
    }

    /**
     * Возвращает элемент <E> массива по заданному индексу
     * @param position
     * @return
     */
    public E get(int position) {
        return (E) this.objects[position];
    }
}
