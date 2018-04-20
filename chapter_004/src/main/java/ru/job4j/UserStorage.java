package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserStorage {
    private static final Logger Log = LoggerFactory.getLogger(UserStorage.class);

    private static final UserStorage instance = new UserStorage();
    private List<Users> users = new CopyOnWriteArrayList<>();

    private UserStorage() {
    }

    public static UserStorage getInstance() {
        return instance;
    }

    public void add(Users user) {
        this.users.add(user);
    }

    public List<Users> getUsers() {
        return this.users;
    }
}
