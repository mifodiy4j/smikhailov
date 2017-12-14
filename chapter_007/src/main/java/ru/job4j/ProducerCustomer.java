package ru.job4j;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerCustomer<T> {

    private Queue<T> queue = new LinkedList<T>();
    private int capacity;

    private final Object lock = new Object();
    private boolean blockCustomer = true;

    public ProducerCustomer(int capacity) {
        this.capacity = capacity;
    }

    public void put(T element) throws InterruptedException {
        synchronized (this.lock) {

            while (queue.size() == capacity) {
                System.out.println(String.format("%s wait to put : %s", Thread.currentThread().getId(), element));
                lock.wait();
            }
            System.out.println(String.format("%s put element : %s", Thread.currentThread().getId(), element));
            queue.add(element);

            System.out.println(String.format("%s notify that it is possible to take element", Thread.currentThread().getId()));
            this.lock.notify();
        }
    }

    public T take() throws InterruptedException {
        synchronized (this.lock) {

            while (queue.isEmpty()) {
                System.out.println(String.format("%s wait, because collection contains no elements", Thread.currentThread().getId()));
                lock.wait();
            }
            T item = queue.remove();
            System.out.println(String.format("%s take element : %s", Thread.currentThread().getId(), item));

            System.out.println(String.format("%s notify that it is possible to put element", Thread.currentThread().getId()));
            this.lock.notify();
            return item;
        }
    }
}
