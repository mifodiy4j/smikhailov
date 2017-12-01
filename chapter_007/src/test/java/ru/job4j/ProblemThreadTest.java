package ru.job4j;

import org.junit.Test;

public class ProblemThreadTest {

    @Test
    public void start() {
        ProblemThread problemThread = new ProblemThread();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000_000; i++) {
                    problemThread.k++;
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        System.out.printf("%s %n", "Start programme");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%d %n", problemThread.k);
        System.out.printf("%s %n", "Stop programme");
    }
}