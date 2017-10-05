package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IteratorEvenNumberArrayTest {
    @Test
    public void whenCheckNextPositionShouldReturnContentValue() {
        IteratorEvenNumberArray it = new IteratorEvenNumberArray(new int[] {4, 2, 1, 1});

        it.next();
        //it.next();
        int result = (Integer) it.next();

        Assert.assertThat(result, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCheckNextPositionAfterEnd() {
        IteratorEvenNumberArray it = new IteratorEvenNumberArray(new int[] {4, 2, 1, 1});

        it.next(); //4
        it.next(); //2
        int result = (Integer) it.next(); //hasn't element

    }

    @Test
    public void whenGetNextCallShouldPointerForward() {
        IteratorEvenNumberArray it = new IteratorEvenNumberArray(new int[] {4, 2, 1, 1});

        it.next(); //4
        it.next(); //2
        it.hasNext();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}