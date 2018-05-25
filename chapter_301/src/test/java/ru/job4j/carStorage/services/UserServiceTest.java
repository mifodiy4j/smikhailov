package ru.job4j.carStorage.services;

import org.junit.Test;
import ru.job4j.carStorage.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserServiceTest {

    private final UserService users = UserService.getInstance();

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setLogin("test");
        user = this.users.save(user);
        assertThat(user, is(this.users.findById(user.getId())));
    }

    @Test
    public void testGetAll() throws Exception {
        User user = new User();
        user.setLogin("test");
        this.users.save(user);
        assertTrue(this.users.getAll().contains(user));
    }
}
