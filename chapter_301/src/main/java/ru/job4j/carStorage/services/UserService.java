package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.UserDAO;
import ru.job4j.carStorage.models.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO = UserDAO.getInstance();

    public static final UserService instance = new UserService();

    public static UserService getInstance() {
        return instance;
    }

    public User save(final User user) {
        userDAO.openCurrentSessionwithTransaction();
        userDAO.save(user);
        userDAO.closeCurrentSessionwithTransaction();
        return user;
    }

    public List<User> getAll() {
        userDAO.openCurrentSessionwithTransaction();
        List<User> users = userDAO.getAll();
        userDAO.closeCurrentSessionwithTransaction();
        return users;
    }

    public User findById(int id) {
        userDAO.openCurrentSessionwithTransaction();
        User user = userDAO.findById(id).get();
        userDAO.closeCurrentSessionwithTransaction();
        return user;
    }
}
