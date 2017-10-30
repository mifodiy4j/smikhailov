package ru.job4j;

import org.junit.Test;

/**
 * Created by SERG on 30.10.2017.
 */
public class SimpleSetSlowTest {
    @Test
    public void testInsertionWillGrow() {
        long start, end;
        final SimpleSet<String> ml = new SimpleSet<>();
        ml.add("0");
        ml.add("1");
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            ml.add(Integer.toString(i + 2));
        }
        end = System.nanoTime();
        System.out.printf("%,12d ns", end - start);
    }
}