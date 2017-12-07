package ru.job4j;

import org.junit.Test;

public class UserStorageTest {

    @Test
    public void when() throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        UserStorage.User userA = new UserStorage.User(1, 1000);
        UserStorage.User userB = new UserStorage.User(2, 2000);

        userStorage.add(userA);
        userStorage.add(userB);

        Thread t1 = new Thread(new UserStorage.Tester(userA, userB, 150));
        Thread t2 = new Thread(new UserStorage.Tester(userB, userA, 250));

        t1.start();
        t2.start();
    }

}