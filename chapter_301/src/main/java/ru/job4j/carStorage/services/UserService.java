package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.UserDAO;
import ru.job4j.carStorage.models.User;
import java.util.List;

public class UserService {

    private final UserDAO userDAO = UserDAO.getInstance();
    private static final UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
    }

    public User save(final User user) {
        userDAO.save(user);
        return user;
    }

    public List<User> getAll() {
        List<User> users = userDAO.getAll();
        return users;
    }

    public User findById(int id) {
        User user = userDAO.findById(id).get();
        return user;
    }
}
