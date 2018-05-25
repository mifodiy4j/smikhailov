package ru.job4j.carStorage.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    private static final SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

    public HibernateFactory() {
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
