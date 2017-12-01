package ru.job4j;

public class Time implements Runnable{
    @Override
    public void run() {
        System.out.printf("Поток { %s } начал работу%n", Thread.currentThread().getName());
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Поток { %s } отработал%n", Thread.currentThread().getName());
    }
}
