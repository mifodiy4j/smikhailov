package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorFor2Array implements Iterator {

    private final int[][] value;
    private int indexX = 0;
    private int indexY = 0;

    public IteratorFor2Array(final int[][] value) {
        this.value = value;
    }

    public boolean hasNext() {
        if (value[indexY].length > indexX) {
            return true;
        } else {
            return value.length > (indexY + 1);
        }
    }

    public Object next() {
        if (indexX == value[indexY].length) {
            indexX = 0;
            if (indexY == value.length - 1) {
                throw new NoSuchElementException();
            } else {
                return value[++indexY][indexX++];
            }
        } else {
            return value[indexY][indexX++];
        }
    }
}