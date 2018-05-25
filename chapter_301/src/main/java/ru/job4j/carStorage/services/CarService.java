package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.CarDAO;
import ru.job4j.carStorage.models.Car;

import java.util.List;

public class CarService {

    private final CarDAO carDAO = CarDAO.getInstance();

    public static final CarService instance = new CarService();

    public static CarService getInstance() {
        return instance;
    }

    public Car save(Car car) {
        carDAO.openCurrentSessionwithTransaction();
        carDAO.save(car);
        carDAO.closeCurrentSessionwithTransaction();
        return car;
    }

    public void update(Car car) {
        carDAO.openCurrentSessionwithTransaction();
        carDAO.update(car);
        carDAO.closeCurrentSessionwithTransaction();
    }

    public List<Car> getAll() {
        carDAO.openCurrentSessionwithTransaction();
        List<Car> cars = carDAO.getAll();
        carDAO.closeCurrentSessionwithTransaction();
        return cars;
    }

    public Car findById(final int id) {
        carDAO.openCurrentSessionwithTransaction();
        Car car = carDAO.findById(id).get();
        carDAO.closeCurrentSessionwithTransaction();
        return car;
    }

    public List<Car> findWithFoto() {
        carDAO.openCurrentSessionwithTransaction();
        List<Car> cars = carDAO.findWithFoto();
        carDAO.closeCurrentSessionwithTransaction();
        return cars;
    }

    public List<Car> findForLastDay() {
        carDAO.openCurrentSessionwithTransaction();
        List<Car> cars = carDAO.findForLastDay();
        carDAO.closeCurrentSessionwithTransaction();
        return cars;
    }

    public List<String> getListBrand() {
        carDAO.openCurrentSessionwithTransaction();
        List<String> brands = carDAO.getListBrand();
        carDAO.closeCurrentSessionwithTransaction();
        return brands;
    }

    public List<Car> findWithSpecificBrand(final String brand) {
        carDAO.openCurrentSessionwithTransaction();
        List<Car> cars = carDAO.findWithSpecificBrand(brand);
        carDAO.closeCurrentSessionwithTransaction();
        return cars;
    }
}
