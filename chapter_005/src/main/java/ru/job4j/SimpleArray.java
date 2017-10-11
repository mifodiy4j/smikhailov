package ru.job4j;

public class SimpleArray<E> {

    Object[] objects;
    int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(E value) {
        this.objects[index++] = value;
        if(index >= objects.length) {
            Object[] newObjects = new Object[objects.length * 3 / 2 + 1];
            for (int i = 0; i < objects.length; i++) {
                newObjects[i] = objects[i];
            }
            objects = newObjects;
        }
    }

    public void update(int position, E newValue) {
        this.objects[position] = newValue;
    }

    public void delete(int position) {
        for (int i = position; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
        if (index >= position) {
            index--;
        }
    }

    public E get(int position) {
        return (E) this.objects[position];
    }
}
