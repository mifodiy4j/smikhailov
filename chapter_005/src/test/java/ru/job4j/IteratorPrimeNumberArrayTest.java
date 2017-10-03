package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorPrimeNumberArrayTest {
    @Test
    public void whenGetNextCallShouldPointerForward() {
        IteratorPrimeNumberArray it = new IteratorPrimeNumberArray(new int[] {3, 4, 5, 6, 7});

        it.next();
        it.next();
        int result = (Integer) it.next();

        Assert.assertThat(result, is(7));
    }

    @Test
    public void whenCheckNextPositionShouldReturnContentValue() {
        IteratorPrimeNumberArray it = new IteratorPrimeNumberArray(new int[] {3, 4, 5, 6, 7});

        it.next(); //3
        it.next(); //5
        it.next(); //7
        it.hasNext();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}