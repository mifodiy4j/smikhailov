package ru.job4j;

import java.util.concurrent.locks.ReentrantLock;

public class BombermanField {

    private final int length;

    private final int height;

    public volatile ReentrantLock[][] board;

    public BombermanField(int length, int height) {
        this.length = length;
        this.height = height;

        board = new ReentrantLock[this.length][this.height];
        for(int i = 0; i < this.length; i++) {
            for(int j = 0; j < this.height; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }
}
