package ru.job4j.storage;

import ru.job4j.storage.model.User;
import java.util.List;

public interface Storage {

    void add(User user);

    List<User> getAll();

    void delete(User user);
}
