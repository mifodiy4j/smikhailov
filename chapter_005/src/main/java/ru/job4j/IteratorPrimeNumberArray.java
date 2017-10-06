package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorPrimeNumberArray implements Iterator {

    private final int[] value;
    private int index = 0;
    private int currentIndexEventNumber = 0;

    public IteratorPrimeNumberArray(int[] value) {
        this.value = value;
    }

    @Override
    public boolean hasNext() {
        for (int i = index; i < value.length; i++) {
            boolean markerCheckPrime = true;
            for (int d = 2; d * d <= value[i]; d++) {
                if (value[i] % d == 0) {
                    markerCheckPrime = false;
                    break;
                }
            }
            if(markerCheckPrime) {
                index = i;
                currentIndexEventNumber = i;
                return true;
            }
        }
        return false;
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
