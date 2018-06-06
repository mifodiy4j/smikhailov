package ru.job4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.Car;
import ru.job4j.storage.CarDAO;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarDAO carDAO = CarDAO.getInstance();

    public void add(Car car) {
        carDAO.save(car);
    }

    public List<Car> getAll() {
        return (List<Car>) carDAO.getAll();
    }
}
