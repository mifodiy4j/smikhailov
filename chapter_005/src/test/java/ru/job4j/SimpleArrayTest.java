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
    public void whenCreateContainterMoreGivenSize() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("test0"); //0
        simpleArray.add("test1"); //1
        simpleArray.add("test2"); //2
        simpleArray.add("test3"); //3
        simpleArray.add("test4"); //4
        simpleArray.add("test5"); //5
        String result = simpleArray.get(5);

        assertThat(result, is("test5"));
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
    public void whenDeleteElementControlElementWithDeleteIndex() {
        SimpleArray<Double> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1.0);
        simpleArray.add(2.2);
        simpleArray.add(3.3);
        simpleArray.add(4.4);
        simpleArray.delete(1);
        double result = simpleArray.get(1);

        assertThat(result, is(3.3));
    }

    @Test
    public void whenDeleteElementControlElementWithLastIndex() {
        SimpleArray<Double> simpleArray = new SimpleArray<>(4);
        simpleArray.add(1.0); //0
        simpleArray.add(2.2); //1
        simpleArray.add(3.3); //2
        simpleArray.add(4.4); //3
        simpleArray.delete(1);
        double result = simpleArray.get(2);

        assertThat(result, is(4.4));
    }
}