package ru.job4j;

import org.junit.Test;

public class BombermanTest {
    @Test
    public void testIsTheMoveCorrect() {
        BombermanField bf = new BombermanField(3, 3);

        Bomberman b = new Bomberman(1, 1, bf.board);

        try {
            b.bombermanMove(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            b.bombermanMove(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToAccessTwoThread() throws InterruptedException {
        BombermanField bf = new BombermanField(3, 3);

        Bomberman b = new Bomberman(1, 1, bf.board);

        Thread moveLeft = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    b.bombermanMove(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread moveUp = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    b.bombermanMove(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        moveLeft.start();
        moveUp.start();

        moveLeft.join();
        moveUp.join();
    }
}