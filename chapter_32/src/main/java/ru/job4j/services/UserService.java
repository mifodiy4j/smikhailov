package ru.job4j.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ru.job4j.models.User;
import ru.job4j.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-data-context.xml");
    private UserRepository userRepository = context.getBean(UserRepository.class);

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }
}
