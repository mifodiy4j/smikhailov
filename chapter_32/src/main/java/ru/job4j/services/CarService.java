package ru.job4j.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ru.job4j.models.Car;
import ru.job4j.repository.CarRepository;
import java.util.List;

@Service
public class CarService {

    private ApplicationContext context = new ClassPathXmlApplicationContext("hibernate-data-context.xml");
    private CarRepository carRepository = context.getBean(CarRepository.class);

    public List<Car> getAll() {
        return (List<Car>) carRepository.findAll();
    }

    /*@Autowired
    private CarDAO carDAO = CarDAO.getInstance();

    public void add(Car car) {
        carDAO.save(car);
    }

    *//*@Transactional()*//*
    public List<Car> getAll() {
        return (List<Car>) carDAO.getAll();
    }*/
}
