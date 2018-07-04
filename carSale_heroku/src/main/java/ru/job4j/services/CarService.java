package ru.job4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.Car;
import ru.job4j.repository.CarRepository;
import java.util.List;

@Service
public class CarService {

    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> getAll() {
        return (List<Car>) this.repository.findAll();
    }

    public Car add(Car car) {
        return this.repository.save(car);
    }
}
