package ru.job4j;

import java.util.Calendar;

/**
 * @author smikhailov
 * @since  30.10.2017
 */
public class MapTest {
    public static final class User {
        String name;
        int children;
        Calendar birthday;

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }
    }
}
