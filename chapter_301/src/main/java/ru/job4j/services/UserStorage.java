package ru.job4j.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.User;

import java.sql.Timestamp;
import java.util.List;

public class UserStorage {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        //User user = new User();

        //user.setLogin("test_2");
        //session.save(user);

        /*user.setId(1);
        user.setLogin("test_1");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        session.update(user);*/

       /* user.setId(1);
        session.delete(user);*/

        List<User> users = session.createQuery("from User ").list();
        for (User user : users) {
            System.out.println(user.getLogin());
        }


        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
