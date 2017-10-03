package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IteratorEvenNumberArrayTest {
    @Test
    public void whenGetNextCallShouldPointerForward() {
        IteratorEvenNumberArray it = new IteratorEvenNumberArray(new int[] {4, 2, 1, 1});

        it.next();
        int result = (Integer) it.next();

        Assert.assertThat(result, is(2));
    }

    @Test
    public void whenCheckNextPositionShouldReturnContentValue() {
        IteratorEvenNumberArray it = new IteratorEvenNumberArray(new int[] {4, 2, 1, 1});

        it.next(); //4
        it.next(); //2
        it.hasNext();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}