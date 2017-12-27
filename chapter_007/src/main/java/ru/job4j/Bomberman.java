package ru.job4j;

import java.util.concurrent.locks.ReentrantLock;

public class Bomberman {

    private int x;
    private int y;
    private volatile ReentrantLock[][] board;

    public Bomberman(int x, int y, ReentrantLock[][] board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void bombermanMove(int typeMoving) throws InterruptedException {
        int xNew = 0;
        int yNew = 0;

        System.out.printf("%s x = %d y = %d %n", Thread.currentThread().getName(), this.x, this.y);

        if (typeMoving == 0) { // up
            xNew = this.x;
            yNew = this.y + 1;
        } else if (typeMoving == 1) { //down
            xNew = this.x;
            yNew = this.y - 1;
        } else if (typeMoving == 2) { //left
            xNew = this.x - 1;
            yNew = this.y;
        } else if (typeMoving == 3) { // right
            xNew = this.x + 1;
            yNew = this.y;
        }

        if (moveIsRight(xNew, yNew)) {
            this.board[this.x][this.y].lock();
            if (this.board[xNew][yNew].tryLock()) {
                Thread.currentThread().sleep(500);
                this.board[this.x][this.y].unlock();
                setX(xNew);
                setY(yNew);
                System.out.printf("New position x = %d y = %d %n", this.x, this.y);
            }
        } else {
            System.out.printf("Invalid moving %n");
        }
    }

    private boolean moveIsRight(int x, int y) {
        boolean result = true;
        if (x > board.length - 1 || y > board[0].length - 1 || x < 0 || y < 0) {
            result = false;
        }
        return result;
    }
}
