package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Created by SERG on 07.11.2017.
 */
public class TreeTest {

    @Test
    public void whenInsertSixElementsShouldReturnTheListElements() {

        Tree<String> tree = new Tree<>();

        tree.add("0", "1");
        tree.add("0", "2");
        tree.add("0", "3");
        tree.add("1", "4");
        tree.add("1", "5");
        tree.add("2", "6");

        List<String> result = new ArrayList<>();
        result.add("0");
        result.add("1");
        result.add("2");
        result.add("3");
        result.add("4");
        result.add("5");
        result.add("6");

        Assert.assertThat(result, is(tree.listTree));
    }

    @Test
    public void whenInsertDuplicateElementsShouldReturnTheListElements() {

        Tree<String> tree = new Tree<>();

        tree.add("0", "1");
        tree.add("0", "2");
        tree.add("0", "1");
        tree.add("1", "2");
        tree.add("1", "0");

        List<String> result = new ArrayList<>();
        result.add("0");
        result.add("1");
        result.add("2");

        Assert.assertThat(result, is(tree.listTree));
    }

    @Test
    public void whenInsertElementWhoseParentDoesNotFoundShouldReturnTheListElements() {

        Tree<String> tree = new Tree<>();

        tree.add("0", "1");
        tree.add("0", "3");
        tree.add("2", "6"); //"2" - does not found
        tree.add("1", "4");

        List<String> result = new ArrayList<>();
        result.add("0");
        result.add("1");
        result.add("3");
        result.add("4");

        Assert.assertThat(result, is(tree.listTree));
    }

}