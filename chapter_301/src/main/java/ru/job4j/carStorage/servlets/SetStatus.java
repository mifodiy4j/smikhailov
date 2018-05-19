package ru.job4j.carStorage.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.carStorage.models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        int id = Integer.parseInt(req.getParameter("id"));

        Car carInDB = session.get(Car.class, id);

        User userInDB = carInDB.getAuthor();

        String currentLogin = userInDB.getLogin();
        int currentId = carInDB.getId();
        User user = session.get(User.class, currentId);
        String currentPassword = user.getPassword();

        String testLogin = req.getParameter("author");
        String testPassword = req.getParameter("password");

        if (testLogin.equals(currentLogin) && testPassword.equals(currentPassword)) {

            String status = req.getParameter("status");
            boolean tr = Boolean.parseBoolean(status);

            if (tr) {
                carInDB.setSold(true);
            } else {
                carInDB.setSold(false);
            }

            session.save(carInDB);
        }

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
