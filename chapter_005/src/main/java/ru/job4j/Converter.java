package ru.job4j;

import java.util.*;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            private Iterator<Integer> currentIterator;

            private void selectCurrentIterator() {
                if (currentIterator == null && it.hasNext())
                    currentIterator = it.next();
            }

            @Override
            public boolean hasNext() {
                selectCurrentIterator();
                if(currentIterator == null)
                    return false;
                if(currentIterator.hasNext())
                    return true;

                if(it.hasNext())
                    currentIterator = it.next();

                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                selectCurrentIterator();
                if(currentIterator == null)
                    throw new NoSuchElementException();

                if(!currentIterator.hasNext() && it.hasNext())
                    currentIterator = it.next();

                return currentIterator.next();
            }
        };
    }
}
