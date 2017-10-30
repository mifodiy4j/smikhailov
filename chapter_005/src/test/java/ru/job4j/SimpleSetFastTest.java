package ru.job4j;

import org.junit.Test;

public class SimpleSetFastTest {

    @Test
    public void testInsertionWillGrow() {
        long start, end;
        final SimpleSetFast<String> ml = new SimpleSetFast<>();
        ml.add("0");
        ml.add("1");
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            ml.add(Integer.toString(i + 2));
        }
        end = System.nanoTime();
        System.out.printf("%,d ns", end - start);
    }

}