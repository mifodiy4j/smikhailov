package ru.job4j;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            if (children != user.children) return false;
            if (name != null ? !name.equals(user.name) : user.name != null) return false;
            return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + children;
            result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
            return result;
        }
    }

    @Test
    public  void map() {
        Calendar c1 = new GregorianCalendar(1990, 12, 21);
        User first = new User("Jack", 2, c1);
        User second = new User("Jack", 2, c1);

        Map<User, Object> map = new HashMap<>();
        Object obj = new Object();
        map.put(first, obj);
        map.put(second, obj);

        System.out.println("first.equals(second) = " + first.equals(second));
        System.out.printf("%s %n",map);
        System.out.printf("Size map = %d",map.size());
    }
}