package ru.job4j;

public class CountChar implements Runnable {
    private String str;

    private int count;

    public CountChar(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.printf("Поток COUNT { %s } начал работу %n", Thread.currentThread().getName());
            while (!Thread.currentThread().isInterrupted()) {
                this.count(str);
            }
            System.out.printf("Поток COUNT { %s } отработал %n", Thread.currentThread().getName());
        }
    }

    public void count(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && str.charAt(i + 1) != ' ') {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.printf("Времени на подсчет не хватило %n");
                    e.printStackTrace();
                }
                count++;
                System.out.printf("Количество пробелов посчитано = %d  %n", count);
            }
        }
        Thread.currentThread().interrupt();
    }

    public int getCount() {
        return count;
    }
}
