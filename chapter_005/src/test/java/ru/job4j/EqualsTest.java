package ru.job4j;

import org.junit.Test;

import java.util.Calendar;

/**
 * Created by SERG on 01.11.2017.
 */
public class EqualsTest {

    public static final class User {
        String name;
        int age;


        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof User)) return false;

            User user = (User) o;

            if (age != user.age) return false;
            return name != null ? name.equals(user.name) : user.name == null;
        }
    }

    @Test
    public void example() {

        User user1 = new User("Anton", 22);
        User user2 = new User("Anton", 22);

        boolean result = user1.equals(user2);
        System.out.printf("user1.equals(user2) = %b", result);
    }
}
