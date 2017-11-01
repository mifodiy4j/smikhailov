package ru.job4j;

import org.junit.Test;

public class HashCodeTest {
    public static final class User {
        String name;
        int age;


        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + age;
            return result;
        }
    }

    @Test
    public void example() {

        HashCodeTest.User user1 = new HashCodeTest.User("Anton", 22);
        HashCodeTest.User user2 = new HashCodeTest.User("Anton", 22);

        String str = new String();
        HashCodeTest.User user3 = new HashCodeTest.User(str, 0);

        System.out.printf("user1.hashCode() = %d %n", user1.hashCode());
        System.out.printf("user2.hashCode() = %d %n", user2.hashCode());
        System.out.printf("user1.hashCode() == user2.hashCode() = %b %n", user1.hashCode() == user2.hashCode());

        System.out.printf("user3.hashCode() = %d", user3.hashCode());
    }
}
