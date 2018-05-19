package ru.job4j.carStorage.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.carStorage.models.Body;
import ru.job4j.carStorage.models.Car;
import ru.job4j.carStorage.models.Engine;
import ru.job4j.carStorage.models.Transmission;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        Car car = new Car();
        car.setBrand(req.getParameter("brand"));
        car.setModel(req.getParameter("model"));
        Body body = new Body(Integer.parseInt(req.getParameter("body")));
        car.setBody(body);
        car.setYearOfManufacture(Short.parseShort(req.getParameter("yearOfManufacture")));
        car.setMileage(Integer.parseInt(req.getParameter("mileage")));
        Transmission transmission = new Transmission(Integer.parseInt(req.getParameter("transmission")));
        car.setTransmission(transmission);
        Engine engine = new Engine(Integer.parseInt(req.getParameter("engine")));
        car.setEngine(engine);

        car.setDescription(req.getParameter("description"));

        session.save(car);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
