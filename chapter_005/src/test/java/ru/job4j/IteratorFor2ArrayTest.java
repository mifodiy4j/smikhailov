package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorFor2ArrayTest {
    @Test
    public void whenGetNextCallShouldPointerForward() {
        IteratorFor2Array it = new IteratorFor2Array(new int[][] {
                {1, 2},
                {3, 4}
            }
        );

        it.next();
        it.next();
        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(4));
    }

    @Test
    public void whenCheckNextPositionShouldReturnContantValue() {
        IteratorFor2Array it = new IteratorFor2Array(new int[][] {
                {1, 2},
                {3, 4}
            }
        );

        it.next(); //1
        it.next(); //2
        it.next(); //3
        it.next(); //4
        it.hasNext();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}