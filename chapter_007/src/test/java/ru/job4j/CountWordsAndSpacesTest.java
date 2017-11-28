package ru.job4j;

import org.junit.Test;

public class CountWordsAndSpacesTest {

    @Test
    public void whenStringContainsFiveSpacesAndSixWords() {
        String str = "asd fgh hjk klj ghj yuuio";
        CountWordsAndSpaces countWords = new CountWordsAndSpaces(str, "countWords", false);
        CountWordsAndSpaces countSpaces = new CountWordsAndSpaces(str, "countSpaces", true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}