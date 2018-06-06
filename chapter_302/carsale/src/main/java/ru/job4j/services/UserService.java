package ru.job4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.User;
import ru.job4j.storage.UserDAO;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO = UserDAO.getInstance();

    public void add(User user) {
        userDAO.save(user);
    }

    public List<User> getAll() {
        return (List<User>) userDAO.getAll();
    }

}
