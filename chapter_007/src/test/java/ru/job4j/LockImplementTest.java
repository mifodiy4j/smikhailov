package ru.job4j;

import org.junit.Test;

public class LockImplementTest {

    @Test
    public void test() throws InterruptedException {

        LockImplement lockImplement = new LockImplement();
        Common common = new Common();

        for (int i = 1; i < 6; i++) {

            Thread t = new Thread(new CountLock(common, lockImplement));
            t.setName("Поток " + i);
            t.start();
            t.join();  //?
        }
    }
}