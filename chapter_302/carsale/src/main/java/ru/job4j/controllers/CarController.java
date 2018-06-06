package ru.job4j.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.models.Car;
import ru.job4j.services.CarService;
import java.util.List;

@Controller
public class CarController {

    private static final Logger Log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<Car> getAllCars() {
        return this.carService.getAll();
    }
}