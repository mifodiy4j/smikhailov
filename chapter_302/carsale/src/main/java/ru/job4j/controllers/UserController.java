package ru.job4j.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.models.User;
import ru.job4j.services.UserService;

import java.util.List;

@Controller
public class UserController {

    private static final Logger Log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/cars/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }
}