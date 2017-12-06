package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnagrammaTest {

    @Test
    public void whenWordsIsAnagrammsShouldReturnTrue() {
        Anagramma anagramma = new Anagramma("атлас", "салат");

        boolean result = anagramma.wordsIsAnagramm();

        assertThat(result, is(true));
    }

    @Test
    public void whenWordsContainsEqualNumberOfLettersShouldReturnFalse() {
        Anagramma anagramma = new Anagramma("атлас", "салют");

        boolean result = anagramma.wordsIsAnagramm();

        assertThat(result, is(false));
    }

    @Test
    public void whenWordsContainsDifferentNumberOfLettersShouldReturnFalse() {
        Anagramma anagramma = new Anagramma("атлас", "атласный");

        boolean result = anagramma.wordsIsAnagramm();

        assertThat(result, is(false));
    }
}