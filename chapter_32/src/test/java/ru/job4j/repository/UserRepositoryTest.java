package ru.job4j.repository;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.Car;
import ru.job4j.models.User;

import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void getUsers() {
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-data-context.xml");
        UserRepository repository = context.getBean(UserRepository.class);
        List<User> users = (List<User>) repository.findAll();

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getCars() {
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-data-context.xml");
        CarRepository repository = context.getBean(CarRepository.class);
        List<Car> cars = (List<Car>) repository.findAll();

        for (Car car : cars) {
            System.out.print(car.getBrand());
            System.out.print(car.getAuthor());
            System.out.println();
        }
    }

}