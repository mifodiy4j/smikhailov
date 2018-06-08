package ru.job4j.repository;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;

import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void getAllUsers() {
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-data-context.xml");
        UserRepository repository = context.getBean(UserRepository.class);
        List<User> users = (List<User>) repository.findAll();

        for (User user : users) {
            System.out.println(user);
        }
    }

}