package ru.job4j;

public class CountWordsAndSpaces {

    public int countWords(String str) {
        int count = str.split(" ").length;
        return count;
    }

    public int countSpaces(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ' ') {
                count++;
            }
        }
        return count;
    }
}
