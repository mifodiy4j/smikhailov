package ru.job4j;

import org.junit.Test;

public class MyArrayListTest {

    @Test
    public void test() {

        MyArrayList<Integer> ml = new MyArrayList<>();

        for (int i = 0; i < 6; i++) {
            Thread t = new Thread(new CountThread(ml, i));
            t.start();
        }

        Thread time = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 12; i++) {
                    System.out.printf("ячейка %d : %d \n", i, ml.get(i));
                }
            }
        });

        time.start();
        try {
            time.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class CountThread implements Runnable {
        MyArrayList<Integer> ml;
        int x;

        public CountThread(MyArrayList<Integer> ml, int x) {
            this.ml = ml;
            this.x = x;
        }

        @Override
        public void run() {
            System.out.printf("%s Start \n", Thread.currentThread().getName());
            ml.add(x++);
            ml.add(x);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}