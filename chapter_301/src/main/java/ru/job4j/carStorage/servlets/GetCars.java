package ru.job4j.carStorage.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carStorage.models.Car;
import ru.job4j.carStorage.services.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class GetCars extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(GetCars.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarService carService = CarService.getInstance();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        boolean parDay = Boolean.parseBoolean(req.getParameter("day"));
        boolean parFoto = Boolean.parseBoolean(req.getParameter("foto"));
        String parBrand = req.getParameter("brand");

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        PrintWriter writer = resp.getWriter();
        List<Car> cars;

        if (!parDay & !parFoto & parBrand.equals("")) {
            cars = carService.getAll();
        } else if (parDay & !parFoto & parBrand.equals("")) {
            cars = carService.findForLastDay();
        } else if (!parDay & parFoto & parBrand.equals("")) {
            cars = carService.findWithFoto();
        } else if (!parDay & !parFoto & !parBrand.equals("")) {
            cars = carService.findWithSpecificBrand(parBrand);
        } else {
            cars = carService.getAll();
        }
        writer.append(
                ow.writeValueAsString(
                        cars
                ));
        writer.flush();
    }
}
