package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorEvenNumberArray implements Iterator {

    private final int[] value;
    private int index = 0;
    private int currentIndexEventNumber = 0;

    public IteratorEvenNumberArray(int[] value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        boolean status = false;
        for (int i = index; i < value.length; i++) {
            if (value[i] % 2 == 0) {
                currentIndexEventNumber = i;
                status = true;
                break;
            }
        }
        return status;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            index++;
            return value[currentIndexEventNumber];
        } else {
            throw new NoSuchElementException();
        }
    }
}
