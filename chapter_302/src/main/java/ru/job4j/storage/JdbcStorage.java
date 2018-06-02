package ru.job4j.storage;

import ru.job4j.storage.DAO.UserDAO;
import ru.job4j.storage.model.User;

import java.util.List;

public class JdbcStorage implements Storage{

    private UserDAO userDAO = UserDAO.getInstance();

    @Override
    public void add(User user) {
        userDAO.save(user);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userDAO.getAll();
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }
}
