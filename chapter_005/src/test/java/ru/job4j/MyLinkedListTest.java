package ru.job4j;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by SERG on 13.10.2017.
 */
public class MyLinkedListTest {
    @Test
    public void testInsertionWillGrow() {
        final MyLinkedList<String> ml = new MyLinkedList<>();
        ml.add("0");
        ml.add("1");
        for (int i = 0; i < 100; i++) {
            ml.add(Integer.toString(i + 2));
            String result = ml.get(i + 2);
            assertThat(result, is(Integer.toString(i + 2)));
        }
    }

    @Test
    public void testIteration() {
        final MyLinkedList<String> ml = new MyLinkedList<>();
        ml.add("0");
        ml.add("1");
        ml.add("2");
        ml.add("3");
        ml.add("4");
        final Iterator<String> iter = ml.iterator();
        int result = 0;
        while (iter.hasNext()) {
            iter.next();
            result++;
        }
        assertThat(result, is(4));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetElementWhenIndexThisElementGoesOutSize() {
        final MyLinkedList<String> ml = new MyLinkedList<>();
        ml.add("0");
        ml.add("1");
        ml.add("2");

        String result = ml.get(3);
    }
}