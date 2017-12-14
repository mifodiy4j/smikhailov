package ru.job4j;

import org.junit.Test;

public class ProducerCustomerTest {

    @Test
    public void testQueueWithCapacityOfThreeElements() throws InterruptedException {
        ProducerCustomer<Integer> producerCustomer = new ProducerCustomer<>(3);

        Thread producer = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 4; i++) {
                        producerCustomer.put(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread customer = new Thread() {
            int t;
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    t = producerCustomer.take();
                    Thread.sleep(1000);
                    t = producerCustomer.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Take element : %s \n", t);
            }
        };

        producer.start();
        customer.start();

        producer.join();
        customer.join();
    }
}