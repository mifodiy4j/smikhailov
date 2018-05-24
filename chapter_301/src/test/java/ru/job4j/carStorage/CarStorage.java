package ru.job4j.carStorage.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.carStorage.models.Car;

public class CarStorage {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

//        Car car = session.get(Car.class, 1);
//        System.out.println(car);

        System.out.println(session.createQuery("from Car").list());

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
