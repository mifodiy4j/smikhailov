package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DirectoryTest {

    @Test
    public void whenInsertElementsLessThenTheLoadFactorShouldReturnContentValueForTheKey() {

        Directory<String, Integer> directory = new Directory<>();

        directory.insert("0", 1000);
        directory.insert("1", 1111);
        directory.insert("2", 2222);
        directory.insert("3", 3333);
        directory.insert("4", 4444);
        directory.insert("5", 5555);
        directory.insert("6", 6666);

        int result = directory.get("6");
        Assert.assertThat(result, is(6666));
    }

    @Test
    public void whenInsertElementsMoreThenTheLoadFactorShouldReturnContentValueForTheKey() {

        Directory<String, Integer> directory = new Directory<>();

        directory.insert("0", 1000);
        directory.insert("1", 1111);
        directory.insert("2", 2222);
        directory.insert("3", 3333);
        directory.insert("4", 4444);
        directory.insert("5", 5555);
        directory.insert("6", 6666);
        directory.insert("7", 7777);
        directory.insert("8", 8888);

        int result = directory.get("8");
        Assert.assertThat(result, is(8888));
    }

    @Test
    public void whenInsertElementsWithDuplicateKeyShouldReturnContentValueWhichLastAdd() {

        Directory<String, Integer> directory = new Directory<>();

        directory.insert("0", 1000);
        directory.insert("1", 1111);
        directory.insert("2", 2222);
        directory.insert("3", 3333);
        directory.insert("4", 4444);
        directory.insert("5", 5555);
        directory.insert("6", 6666);
        directory.insert("6", 6);

        int result = directory.get("6");
        Assert.assertThat(result, is(6));
    }

    @Test (expected = NullPointerException.class)
    public void whenDeleteElementShouldReturn() {

        Directory<String, Integer> directory = new Directory<>();

        directory.insert("0", 1000);
        directory.insert("1", 1111);
        directory.insert("2", 2222);
        directory.insert("3", 3333);
        directory.insert("4", 4444);
        directory.insert("5", 5555);
        directory.insert("6", 6666);
        directory.delete("2");

        int result = directory.get("2");
        Assert.assertThat(result, is(2222));
    }

}