package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@ThreadSafe
public class UserStorage {

    Set<User> storage = new HashSet<>();

    synchronized boolean add(User user) {
        return storage.add(user);
    }

    synchronized boolean update(User user) {
        return storage.add(user);
    }

    synchronized boolean delete(User user) {
        return storage.remove(user);
    }

    synchronized boolean transfer(int fromId, int toId, int amount) {

        User userFrom = new User(fromId, 0);
        User userTo = new User(toId, 0);
        boolean markerUserFrom = false;
        boolean markerUserTo = false;

        for (Iterator<User> iter = storage.iterator(); iter.hasNext(); ) {
            if (userFrom.equals(iter.next())) {
                userFrom = iter.next();
                markerUserFrom = true;
            }

            if (userTo.equals(iter.next())) {
                userTo = iter.next();
                markerUserTo = true;
            }
        }

        if (!markerUserFrom || !markerUserTo) {
            return false;
        }

        int temp = userFrom.getAmount();
        temp = temp - amount;
        if (temp < 0 ) {
            return false;
        }
        userFrom.setAmount(temp - amount);

        temp = userTo.getAmount();
        userTo.setAmount(temp + amount);

        return true;
    }

    public static class Tester implements Runnable {

        UserStorage userStorage = new UserStorage();

        private User user1;
        private User user2;
        private int value;

        public Tester(User user1, User user2, int value) {
            this.user1 = user1;
            this.user2 = user2;
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " User1 amount : " + user1.getAmount());
            System.out.println(Thread.currentThread().getName() + " User2 amount : " + user1.getAmount());
            System.out.println(Thread.currentThread().getName() + " Value transfer : " + value);

            System.out.println(Thread.currentThread().getName() + " Start transfer");
            System.out.println("Result" + userStorage.transfer(user1.id, user2.id, value));
        }
    }

    static class User {
        private int id;
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return id == user.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}
