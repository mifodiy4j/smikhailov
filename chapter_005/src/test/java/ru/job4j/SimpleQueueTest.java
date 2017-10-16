package ru.job4j;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by SERG on 16.10.2017.
 */
public class SimpleQueueTest {

    @Test
    public void whenPushOneElementAndPollOneElement() {
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        simpleQueue.push("1");
        String result = simpleQueue.poll();

        assertThat(result, is("1"));
    }

    @Test
    public void whenPushThreeElementAndPollOneElement() {
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        simpleQueue.push("1");
        simpleQueue.push("2");
        simpleQueue.push("3");
        String result = simpleQueue.poll();

        assertThat(result, is("1"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenPollAllElement() {
        SimpleQueue<String> simpleQueue = new SimpleQueue<>();
        simpleQueue.push("1");
        simpleQueue.push("2");
        simpleQueue.push("3");
        simpleQueue.push("4");
        String result = simpleQueue.poll(); //"1"
        result = simpleQueue.poll(); //"2"
        result = simpleQueue.poll(); //"3"
        result = simpleQueue.poll(); //"4"
        result = simpleQueue.poll();

    }

}