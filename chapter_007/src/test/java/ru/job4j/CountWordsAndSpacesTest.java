package ru.job4j;

import org.junit.Test;

public class CountWordsAndSpacesTest {

    @Test
    public void whenStringContainsFiveSpacesAndSixWords() throws InterruptedException {
        String str = "asd fgh hjk klj ghj yuuio";
        CountWordsAndSpaces countWS = new CountWordsAndSpaces();

        Thread wordsThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.printf("Words count : %s %n", countWS.countWords(str));
                    }
                }
        );

        Thread spacesThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.printf("Spaces count : %s %n", countWS.countSpaces(str));
                    }
                }
        );

        System.out.printf("%s %n", "Start programme");

        wordsThread.start();
        spacesThread.start();
        try {
            wordsThread.join();
            spacesThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s %n", "Stop programme");
    }
}