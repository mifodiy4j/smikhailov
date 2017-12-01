package ru.job4j;

public class CountChar implements Runnable {
    private String str;

    public CountChar(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        System.out.printf("Поток { %s } начал работу%n", Thread.currentThread().getName());
        this.count(str);
        System.out.printf("Поток { %s } отработал%n", Thread.currentThread().getName());
    }

    public void count(String str) {
        int count = str.split(" ").length;
        System.out.printf("Число слов : %s %n", count);
    }
}
