package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MyArrayListTest {
    @Test
    public void testInsertionWillGrow() {
        final MyArrayList<String> ml = new MyArrayList<>();
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
        final MyArrayList<String> ml = new MyArrayList<>();
        ml.add("");
        ml.add("");
        ml.add("");
        final Iterator<String> iter = ml.iterator();
        int result = 0;
        while (iter.hasNext()) {
            iter.next();
            result++;
        }
        assertThat(result, is(3));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetElementWhenIndexThisElementGoesOutSize() {
        final MyArrayList<String> ml = new MyArrayList<>();
        ml.add("0");
        ml.add("1");
        ml.add("2");

        String result = ml.get(3);
    }

}