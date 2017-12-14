package ru.job4j;

public class CountChar implements Runnable {
    private String str;
    private int count;
    private boolean stringContainsSlash = true;

    public CountChar(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            System.out.printf("Поток COUNT { %s } начал работу %n", Thread.currentThread().getName());

            while (stringContainsSlash) {
                this.count(str);
            }

            System.out.printf("Поток COUNT { %s } отработал %n", Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
    }

    public void count(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ' && count != 0) {
                str = str.substring(i + 1);
                count++;
                break;
            }
        }
    }
}
