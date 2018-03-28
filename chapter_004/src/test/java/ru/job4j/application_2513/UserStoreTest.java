package ru.job4j.application_2513;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenDBHasSixElement() {

        List<Integer> list;

        list = UserStore.INSTANCE.getListId();

        System.out.println(list);

        System.out.println(list.size());

        assertThat(list.size(), is(8));
    }

    @Test
    public void whenDBHasElementWithIdIsTwo() {

        User user;

        user = UserStore.INSTANCE.selectById(60);

        System.out.println(user);
    }

    @Test
    public void whenPrintAllElementsDB() {

        List<Integer> list;

        list = UserStore.INSTANCE.getListId();

        for (int i : list) {
            User user = UserStore.INSTANCE.selectById(i);
            System.out.println(user);
        }
    }

    @Test
    public void testAdd() {

        int temp = UserStore.INSTANCE.add("name_1","login_1", "email_1", null);

        System.out.println(temp);
    }
}