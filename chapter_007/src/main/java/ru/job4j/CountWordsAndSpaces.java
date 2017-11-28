package ru.job4j;

public class CountWordsAndSpaces implements Runnable {
    private String str;
    private boolean status;
    private int countWords;
    private int countSpaces;

    Thread t;

    public CountWordsAndSpaces(String str, String name, boolean status) {
        this.str = str;
        this.status = status;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {

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
