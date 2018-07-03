package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int  limit = 10;

    public synchronized void offer(T value) throws InterruptedException {
        while(this.queue.size() == this.limit) {
            System.out.println(String.format("%s offer wait because size reached the limit %d", Thread.currentThread().getId(), value));
            this.wait();
        }
        if(this.queue.size() == 0) {
            System.out.println(String.format("%s offer notify all %d", Thread.currentThread().getId(), value));
            notifyAll();
        }
        System.out.println(String.format("%s add %d", Thread.currentThread().getId(), value));
        this.queue.add(value);
    }

    public synchronized T poll() throws InterruptedException {
        while(this.queue.size() == 0){
            System.out.println(String.format("%s poll - wait because is size is null", Thread.currentThread().getId()));
            this.wait();
        }
        if(this.queue.size() == this.limit){
            System.out.println(String.format("%s poll - queue size reached the limit", Thread.currentThread().getId()));
            notifyAll();
        }
        return this.queue.poll();
    }
}
