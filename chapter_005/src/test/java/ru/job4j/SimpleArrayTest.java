package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenCreateContainterShouldReturnTheSameType() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("test0");
        simpleArray.add("test1");
        simpleArray.add("test2");
        String result = simpleArray.get(1);

        assertThat(result, is("test1"));
    }

    @Test
    public void whenUpdateElement() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.update(2,13);
        int result = simpleArray.get(2);

        assertThat(result, is(13));
    }

    @Test
    public void whenDeleteElement() {
        SimpleArray<Double> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1.0);
        simpleArray.add(2.2);
        simpleArray.add(3.3);
        simpleArray.add(4.4);
        simpleArray.delete(1);
        double result = simpleArray.get(1);

        assertThat(result, is(3.3));
    }

}