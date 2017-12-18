package ru.job4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerThreadPool {

    static ExecutorService executor = Executors.newFixedThreadPool(5);

    static void add(WorkerThread worker) {
        executor.execute(worker);
    }

    public static void main(String[] args) {
        for (int i = 0;i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            add((WorkerThread) worker);
        }
        executor.shutdown();
        while(!executor.isTerminated()){}

        System.out.println("Finished all threads");
    }
}
