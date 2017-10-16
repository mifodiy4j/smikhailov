package ru.job4j;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by SERG on 16.10.2017.
 */
public class SimpleStackTest {

    @Test
    public void whenDeleteOneElement() {
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push("1");
        simpleStack.push("2");
        simpleStack.push("3");
        simpleStack.push("4");
        String result = simpleStack.poll();

        assertThat(result, is("4"));
    }

    @Test (expected = EmptyStackException.class)
    public void whenDeleteAllElements() {
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push("1");
        simpleStack.push("2");
        simpleStack.push("3");
        simpleStack.push("4");
        String result = simpleStack.poll(); //"4"
        result = simpleStack.poll(); //"3"
        result = simpleStack.poll(); //"2"
        result = simpleStack.poll(); //"1"
        result = simpleStack.poll();

    }

}