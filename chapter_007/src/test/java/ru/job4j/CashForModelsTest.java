package ru.job4j;

import org.junit.Test;

public class CashForModelsTest {

    @Test
    public void test() throws InterruptedException {
        CashForModels cashForModels = new CashForModels();

        Model model = new Model(111111, "NoName", 30);

        cashForModels.add(model);

        for (int i = 1; i < 6; i++) {
            model.setAge(40 + i);
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    cashForModels.update(model);
                }
            });

            t.setName("Поток " + i);

            t.start();
            System.out.printf("Поток : %s значение : %d %n", Thread.currentThread().getName(), model.getAge());
        }
    }

}