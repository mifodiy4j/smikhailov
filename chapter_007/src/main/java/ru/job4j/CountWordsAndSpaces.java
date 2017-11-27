package ru.job4j;

public class CountWordsAndSpaces implements Runnable {
    private String str;
    private boolean status;
    private int countWords;
    private int countSpaces;

    public CountWordsAndSpaces(String str, boolean status) {
        this.str = str;
        this.status = status;
    }

    @Override
    public void run() {
        System.out.println("TO DO RUN + " + status);

        if (status) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == ' ') {
                    countSpaces++;
                }
            }
            System.out.printf("Count space : %d %n", countSpaces);
        } else {
            countWords = str.split(" ").length;
            System.out.printf("Count words : %d %n", countWords);
        }
    }
}
