package ru.job4j;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();

        //producer
        Thread producer = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<100; i++) {
                        simpleBlockingQueue.offer(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //customer
        Thread customer = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<100; i++) {
                        simpleBlockingQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        producer.start();
        customer.start();

        producer.join();
        customer.join();
    }
}