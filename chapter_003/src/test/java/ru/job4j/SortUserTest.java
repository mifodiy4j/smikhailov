package ru.job4j;

import ru.job4j.models.User;
import java.util.*;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Sergey Mikhailov (mailto:mifodiy67@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {

    /**
     * Test sort.
     */
    @Test
    public void whenSortThreeUsers() {
        SortUser sortUser = new SortUser();

        Set<User> result = new TreeSet<>();
        List<User> list = new ArrayList<>();

        User user1 = new User("Anton", 10);
        list.add(user1);

        User user2 = new User("Tom", 8);
        list.add(user2);

        User user3 = new User("Jack", 27);
        list.add(user3);

        result = sortUser.sort(list);

        Set<User> expected = new TreeSet<>();
        expected.add(user2);
        expected.add(user1);
        expected.add(user3);

        assertThat(result, is(expected));
    }
}