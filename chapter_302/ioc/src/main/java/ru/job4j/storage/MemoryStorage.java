package ru.job4j.storage;

import ru.job4j.storage.model.User;
import java.util.ArrayList;
import java.util.List;

public class MemoryStorage implements Storage {

    private List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        this.users.add(user);
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

    @Override
    public void delete(User user) {
        this.users.remove(user);
    }
}
