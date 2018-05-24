package ru.job4j.carStorage.servlets;

import ru.job4j.carStorage.models.*;
import ru.job4j.carStorage.services.CarService;
import ru.job4j.carStorage.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = UserService.getInstance();

        int id = Integer.parseInt(req.getParameter("id"));

        CarService carService = new CarService();
        Car carInDB = carService.findById(id);

        User userInDB = carInDB.getAuthor();

        String currentLogin = userInDB.getLogin();

        int currentId = carInDB.getId();

        User user = userService.findById(currentId);

        String currentPassword = user.getPassword();

        String testLogin = req.getParameter("author");
        String testPassword = req.getParameter("password");

        if (testLogin.equals(currentLogin) && testPassword.equals(currentPassword)) {

            String status = req.getParameter("status");
            boolean bStatus = Boolean.parseBoolean(status);

            if (bStatus) {
                carInDB.setSold(true);
            } else {
                carInDB.setSold(false);
            }
            carService.update(carInDB);
        }
    }
}
