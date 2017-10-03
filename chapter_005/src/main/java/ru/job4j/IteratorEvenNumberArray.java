package ru.job4j;

import java.util.Iterator;

public class IteratorEvenNumberArray implements Iterator {

    private final int[] value;
    private int index = -1;

    public IteratorEvenNumberArray(int[] value) {
        this.value = value;
        for (int i = 0; i < value.length; i++) {
            if (value[i] % 2 == 0) {
                index = i;
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        boolean status = false;
        if (index != -1) {
            for (int i = index; i < value.length; i++) {
                if (value[i] % 2 == 0) {
                    status = true;
                    break;
                }
            }
        }
        return status;
    }

    @Override
    public Object next() {
        int oldIndex = index;
        if (index != -1) {
            for (int i = index + 1; i < value.length; i++) {
                if (value[i] % 2 == 0) {
                    index = i;
                    break;
                } else {
                    index = -1;
                }
            }
        }
        return value[oldIndex];
    }
}
