package ru.job4j;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConverterTest {

    @Test
    public void whenItHasTwoInnerItControlReturnContentValue() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Arrays.asList(4, 2, 0, 4, 6, 4, 9).iterator(),
                Arrays.asList(0, 9, 8, 7, 5).iterator(),
                Arrays.asList(1, 3, 5, 6, 7, 0, 9, 8, 4).iterator()
        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        convert.next(); //4
        convert.next(); //2
        convert.next(); //0
        convert.next(); //4
        convert.next(); //6
        convert.next(); //4
        convert.next(); //9
        convert.next(); //0
        int result = convert.next(); //9
        assertThat(result, is(9));
    }

    @Test
    public void whenItHasTwoInnerItControlPointerForward() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        convert.next();
        //convert.next();
        boolean result = convert.hasNext();
        assertThat(result, is(false));
    }
}