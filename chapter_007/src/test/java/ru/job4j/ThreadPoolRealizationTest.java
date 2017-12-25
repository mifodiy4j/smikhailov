package ru.job4j;

import org.junit.Test;

public class ThreadPoolRealizationTest {

    @Test
    public void test() throws InterruptedException {
        ThreadPoolRealization thpr = new ThreadPoolRealization(4);
        Work work = new Work(20);
        thpr.addTask(work);
        thpr.execute();
        Thread.currentThread().join();
    }
}