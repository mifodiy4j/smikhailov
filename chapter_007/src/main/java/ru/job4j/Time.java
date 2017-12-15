package ru.job4j;

public class Time implements Runnable {
    int timeDelay;

    public Time(int timeDelay) {
        this.timeDelay = timeDelay;
    }

    @Override
    public void run() {
        System.out.printf("Поток TIME { %s } начал работу %n", Thread.currentThread().getName());
        try {
            Thread.sleep(timeDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Поток TIME { %s } отработал %n", Thread.currentThread().getName());
    }
}
