package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenUpdateUserShouldReturnTheBaseType() {
        UserStore userStore = new UserStore(10);
        User user1 = new User("name1");
        user1.setId("111111");
        userStore.add(user1);
        User user2 = new User("name2");
        user2.setId("222222");
        userStore.add(user2);
        User user3 = new User("name3");
        user3.setId("333333");
        userStore.add(user3);

        User user4 = new User("name4");
        user4.setId("222222");

        Base result = userStore.update(user4);

        assertThat(result, is(user4));
    }

    @Test
    public void whenUpdateUserWhenLastAdd() {
        UserStore userStore = new UserStore(10);
        User user1 = new User("name1");
        user1.setId("111111");
        userStore.add(user1);
        User user2 = new User("name2");
        user2.setId("222222");
        userStore.add(user2);
        User user3 = new User("name3");
        user3.setId("333333");
        userStore.add(user3);

        User user4 = new User("name4");
        user4.setId("333333");

        Base result = userStore.update(user4);

        assertThat(result, is(user4));
    }

    @Test
    public void whenDeleteUserShouldReturnTrue() {
        UserStore userStore = new UserStore(10);
        User user1 = new User("name1");
        user1.setId("111111");
        userStore.add(user1);
        User user2 = new User("name2");
        user2.setId("222222");
        userStore.add(user2);
        User user3 = new User("name3");
        user3.setId("333333");
        userStore.add(user3);

        boolean result = userStore.delete("222222");

        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteUserShouldReturnFalse() {
        UserStore userStore = new UserStore(10);
        User user1 = new User("name1");
        user1.setId("111111");
        userStore.add(user1);
        User user2 = new User("name2");
        user2.setId("222222");
        userStore.add(user2);
        User user3 = new User("name3");
        user3.setId("333333");
        userStore.add(user3);

        boolean result = userStore.delete("2");

        assertThat(result, is(false));
    }
}