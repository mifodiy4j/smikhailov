package ru.job4j;

public class SimpleArray<E> {

    Object[] objects;
    int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(E value) {
        this.objects[index++] = value;
    }

    public void update(int position, E newValue) {
        this.objects[position] = newValue;
    }

    public void delete(int position) {
        for (int i = position; i < objects.length - 1; i++) {
            objects[position] = objects[position + 1];
        }
    }

    public E get(int position) {
        return (E) this.objects[position];
    }
    //add, update, delete, get(int index);
}
