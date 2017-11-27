package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountWordsAndSpacesTest {

    @Test
    public void whenStringContainsFiveSpacesAndSixWords() {
        String str = "asd fgh hjk klj ghj yuuio";
        CountWordsAndSpaces countWords = new CountWordsAndSpaces(str, false);
        new Thread(countWords).start();

        CountWordsAndSpaces countSpaces = new CountWordsAndSpaces(str, true);
        new Thread(countSpaces).start();
    }
}