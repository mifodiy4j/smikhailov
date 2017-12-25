package ru.job4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPoolRealization {

    private BlockingQueue<Task> taskPool;
    private final List<Thread> threadList = new ArrayList<Thread>();

    public ThreadPoolRealization(int size) {
        fillThread(size);
    }

    private void fillThread(int size) {
        for (int i = 0; i < size; i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true)
                            taskPool.take().go();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

    public void addTask(Work work) {
        taskPool = new ArrayBlockingQueue<Task>(work.getTask().length);
        taskPool.addAll(Arrays.asList(work.getTask()));
    }

    public void execute() {
        for (Thread th : threadList)
            th.start();
    }
}

class Work {
    Task[] taskArr;

    public Work(int size) {
        taskArr = new Task[size];
        for (int i = 0; i < size; i++) {
            taskArr[i] = new Task();
        }
    }

    public Task[] getTask() {
        return taskArr;
    }
}

class Task {
    public void go() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
