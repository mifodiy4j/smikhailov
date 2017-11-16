package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
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
        Iterator<String> iter = tree.iterator();
        while(iter.hasNext()) {
            result.add(iter.next());
        }

        List<String> expected = new ArrayList<>();
        expected.add("0");
        expected.add("1");
        expected.add("2");
        expected.add("3");
        expected.add("4");
        expected.add("5");
        expected.add("6");

        Assert.assertThat(result, is(expected));
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
        Iterator<String> iter = tree.iterator();
        while(iter.hasNext()) {
            result.add(iter.next());
        }

        List<String> expected = new ArrayList<>();
        expected.add("0");
        expected.add("1");
        expected.add("2");

        Assert.assertThat(result, is(expected));
    }

    @Test
    public void whenInsertElementWhoseParentDoesNotFoundShouldReturnTheListElements() {

        Tree<String> tree = new Tree<>();

        tree.add("0", "1");
        tree.add("0", "3");
        tree.add("2", "6"); //"2" - does not found
        tree.add("1", "4");

        List<String> result = new ArrayList<>();
        Iterator<String> iter = tree.iterator();
        while(iter.hasNext()) {
            result.add(iter.next());
        }

        List<String> expected = new ArrayList<>();
        expected.add("0");
        expected.add("1");
        expected.add("3");
        expected.add("4");

        Assert.assertThat(result, is(expected));
    }

    @Test
    public void whenInsertElementsRandomlyShouldReturnTheListElements() {

        Tree<String> tree = new Tree<>();

        tree.add("10", "4");
        tree.add("10", "3");
        tree.add("4", "1");
        tree.add("10", "2");
        tree.add("3", "5");

        List<String> result = new ArrayList<>();
        Iterator<String> iter = tree.iterator();
        while(iter.hasNext()) {
            result.add(iter.next());
        }

        List<String> expected = new ArrayList<>();
        expected.add("10");
        expected.add("4");
        expected.add("3");
        expected.add("1");
        expected.add("2");
        expected.add("5");

        Assert.assertThat(result, is(expected));
    }

    @Test
    public void whenInsertIsNotBinaryTreeShouldReturnTheListElements() {

        Tree<String> tree = new Tree<>();

        tree.add("10", "4");
        tree.add("10", "3");
        tree.add("4", "1");
        tree.add("10", "2");
        tree.add("3", "5");

        boolean result = tree.isBinary();

        Assert.assertThat(result, is(false));
    }

}