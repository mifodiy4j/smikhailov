package ru.job4j;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorFor2ArrayTest {
    @Test
    public void whenCheckNextPositionShouldReturnContentValue() {
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

    @Test(expected = NoSuchElementException.class)
    public void whenCheckNextPositionAfterEnd() {
        IteratorFor2Array it = new IteratorFor2Array(new int[][] {
                {1, 2},
                {3, 4}
        }
        );

        it.next(); //1
        it.next(); //2
        it.next(); //3
        it.next(); //3
        int result = (Integer) it.next(); //hasn't element

    }

    @Test
    public void whenGetNextCallShouldPointerForward() {
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