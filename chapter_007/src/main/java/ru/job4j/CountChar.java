package ru.job4j;

public class CountChar implements Runnable {
    private String str;

    private int count;

    public CountChar(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        System.out.printf("Поток COUNT { %s } начал работу %n", Thread.currentThread().getName());
        this.count(str);
        System.out.printf("Поток COUNT { %s } отработал %n", Thread.currentThread().getName());
    }

    public void count(String str) {
        for (int i = 0; i < str.length() && !Thread.currentThread().isInterrupted(); i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
                count++;
                System.out.printf("Количество пробелов посчитано = %d  %n", count);
            }
        }
    }

    public int getCount() {
        return count;
    }
}
