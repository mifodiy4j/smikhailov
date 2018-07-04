package ru.job4j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.domain.Car;
import ru.job4j.services.CarService;

@Controller
public class CarController {

    private final CarService service;

    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/cars")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("cars");
        model.addObject("cars", this.service.getAll());
        return model;
    }

    @PostMapping("/cars")
    public String add(@ModelAttribute Car car) {
        this.service.add(car);
        return "redirect:cars";
    }
}