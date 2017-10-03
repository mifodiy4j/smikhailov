package ru.job4j;

import java.util.Iterator;

public class IteratorPrimeNumberArray implements Iterator {

    private final int[] value;
    private int index = -1;

    public IteratorPrimeNumberArray(int[] value) {
        this.value = value;
        for (int i = 0; i < value.length; i++) {
            boolean marker = true;
            for (int d = 2; d * d < value[i]; d++) {
                if (value[i] % d == 0) {
                    marker = false;
                }
            }
            if (marker) {
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
                boolean marker = true;
                for (int d = 2; d * d < value[i]; d++) {
                    if (value[i] % d == 0) {
                        marker = false;
                    }
                }
                if (marker) {
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
        if (index != -1 && index != value.length -1) {
            for (int i = index + 1; i < value.length; i++) {
                boolean marker = true;
                if (value[i] == 1) {
                    marker = false;
                }
                for (int d = 2; d * d <= value[i]; d++) {
                    if (value[i] % d == 0) {
                        marker = false;
                    }
                }
                if (marker) {
                    index = i;
                    break;
                } else {
                    index = -1;
                }
            }
        } else {index = -1;}
        return value[oldIndex];
    }
}
