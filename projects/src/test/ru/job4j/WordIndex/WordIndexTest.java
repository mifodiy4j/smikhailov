package ru.job4j.WordIndex;

import org.junit.Test;
import ru.job4j.WordIndex.WordIndex;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class WordIndexTest {

    @Test
    public void whenTheFileContainsOneWord() {
        WordIndex wi = new WordIndex();
        wi.loadFile("c:\\1.txt");

        Set<Integer> actual = wi.getIndexes4Word("aaa");
        Set<Integer> expected = new HashSet<>();
        expected.add(1);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheFileContainsThreeWord() {
        WordIndex wi = new WordIndex();
        wi.loadFile("c:\\1.txt");

        Set<Integer> actual = wi.getIndexes4Word("a");
        Set<Integer> expected = new HashSet<>();
        expected.add(3);
        expected.add(4);
        expected.add(10);

        assertThat(actual, is(expected));
    }

    @Test
    public void whenTheFileNotContainsWord() {
        WordIndex wi = new WordIndex();
        wi.loadFile("c:\\1.txt");

        Set<Integer> actual = wi.getIndexes4Word("aaaa");
        Set<Integer> expected = null;

        assertThat(actual, is(expected));
    }
}