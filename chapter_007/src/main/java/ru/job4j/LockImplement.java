package ru.job4j;

public class LockImplement {

    private boolean isLocked = false;

    /**
     * проверяет свободен ли лок? Если да - захватывает, иначе блокируется.
     */
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    /**
     * проверяет владеет ли поток локом? Если да то - освобождает.
     */
    public synchronized void unlock() {
        isLocked = false;
        notify();
    }

    public static void main(String[] args) {
        LockImplement lockImplement = new LockImplement();
        Common common = new Common();

        for (int i = 1; i < 6; i++) {

            Thread t = new Thread(new CountLock(common, lockImplement));
            t.setName("Поток " + i);
            t.start();
        }
    }
}

class Common {
    int x = 0;
}

class CountLock implements Runnable {

    Common com;
    LockImplement l;

    CountLock(Common com, LockImplement l) {
        this.com = com;
        this.l = l;
    }

    public void run() {

        l.lock(); // устанавливаем блокировку
        try {
            com.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), com.x);
                com.x++;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            l.unlock(); // снимаем блокировку
        }
    }
}
