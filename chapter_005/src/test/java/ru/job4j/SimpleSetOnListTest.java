package ru.job4j;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetOnListTest {

    @Test
    public void whenAddFourElement() {
        SimpleSetOnList<String> simpleSetOnList = new SimpleSetOnList<>();

        simpleSetOnList.add("zero");
        simpleSetOnList.add("first");
        simpleSetOnList.add("second");
        simpleSetOnList.add("third");

        final Iterator<String> iter = simpleSetOnList.iterator();
        int result = 0;
        while (iter.hasNext()) {
            iter.next();
            result++;
        }
        assertThat(result, is(4));
    }

    @Test
    public void whenAddDuplicate() {
        SimpleSetOnList<String> simpleSetOnList = new SimpleSetOnList<>();

        simpleSetOnList.add("zero");
        simpleSetOnList.add("first");
        simpleSetOnList.add("second");
        simpleSetOnList.add("third");
        simpleSetOnList.add("first");
        simpleSetOnList.add("zero");

        final Iterator<String> iter = simpleSetOnList.iterator();
        int result = 0;
        while (iter.hasNext()) {
            iter.next();
            result++;
        }
        assertThat(result, is(4));
    }

}