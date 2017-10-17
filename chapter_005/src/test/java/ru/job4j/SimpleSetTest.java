package ru.job4j;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by SERG on 17.10.2017.
 */
public class SimpleSetTest {

    @Test
    public void testIterator() {
        SimpleSet<String> simpleSet = new SimpleSet<>(10);
        simpleSet.add("zero");
        simpleSet.add("first");
        simpleSet.add("second");
        simpleSet.add("third");

        final Iterator<String> iter = simpleSet.iterator();
        int result = 0;
        while (iter.hasNext()) {
            iter.next();
            result++;
        }
        assertThat(result, is(4));
    }

    @Test
    public void whenAddDuplicate() {
        SimpleSet<String> simpleSet = new SimpleSet<>(10);
        simpleSet.add("zero");
        simpleSet.add("first");
        simpleSet.add("second");
        simpleSet.add("third");
        simpleSet.add("first");

        final Iterator<String> iter = simpleSet.iterator();
        int result = 0;
        while (iter.hasNext()) {
            iter.next();
            result++;
        }
        assertThat(result, is(4));
    }

}