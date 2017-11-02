package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetHashTest {

    @Test
    public void whenInsertElementsLessThenTheLoadFactorShouldReturnTheCheckResultForContain() {

        SimpleSetHash<String> simpleSetHash = new SimpleSetHash<>();

        simpleSetHash.add("0");
        simpleSetHash.add("1");
        simpleSetHash.add("2");
        simpleSetHash.add("3");
        simpleSetHash.add("4");
        simpleSetHash.add("5");
        simpleSetHash.add("6");

        boolean result = simpleSetHash.contains("6");
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenInsertElementsMoreThenTheLoadFactorShouldReturnTheCheckResultForContain() {

        SimpleSetHash<String> simpleSetHash = new SimpleSetHash<>();

        simpleSetHash.add("0");
        simpleSetHash.add("1");
        simpleSetHash.add("2");
        simpleSetHash.add("3");
        simpleSetHash.add("4");
        simpleSetHash.add("5");
        simpleSetHash.add("6");
        simpleSetHash.add("7");
        simpleSetHash.add("8");

        boolean result = simpleSetHash.contains("7");
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whendeleteElementShouldReturnTheCheckResultForContain() {

        SimpleSetHash<String> simpleSetHash = new SimpleSetHash<>();

        simpleSetHash.add("0");
        simpleSetHash.add("1");
        simpleSetHash.add("2");
        simpleSetHash.add("3");
        simpleSetHash.add("4");
        simpleSetHash.add("5");
        simpleSetHash.add("6");
        simpleSetHash.add("7");
        simpleSetHash.add("8");

        simpleSetHash.remove("2");

        boolean result = simpleSetHash.contains("2");
        Assert.assertThat(result, is(false));
    }

}