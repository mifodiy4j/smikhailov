package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.CarDAO;
import ru.job4j.carStorage.models.Car;
import java.util.List;

public class CarService {

    private final CarDAO carDAO = CarDAO.getInstance();
    private static final CarService instance = new CarService();

    public static CarService getInstance() {
        return instance;
    }

    public Car save(Car car) {
        carDAO.save(car);
        return car;
    }

    public void update(Car car) {
        carDAO.update(car);
    }

    public List<Car> getAll() {
        List<Car> cars = carDAO.getAll();
        return cars;
    }

    public Car findById(final int id) {
        Car car = carDAO.findById(id).get();
        return car;
    }

    public List<Car> findWithFoto() {
        List<Car> cars = carDAO.findWithFoto();
        return cars;
    }

    public List<Car> findForLastDay() {
        List<Car> cars = carDAO.findForLastDay();
        return cars;
    }

    public List<String> getListBrand() {
        List<String> brands = carDAO.getListBrand();
        return brands;
    }

    public List<Car> findWithSpecificBrand(final String brand) {
        List<Car> cars = carDAO.findWithSpecificBrand(brand);
        return cars;
    }
}
