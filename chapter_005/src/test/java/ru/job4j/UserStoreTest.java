package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenUpdateUserShouldReturnTheBaseType() {
        UserStore userStore = new UserStore(100);
        User user1 = new User("111");
        userStore.add(user1);
        User user2 = new User("222");
        userStore.add(user2);
        User user3 = new User("333");
        userStore.add(user3);

        user2.setId("2");

        Base result = userStore.update(user2);

        assertThat(result, is(user2));
    }
}