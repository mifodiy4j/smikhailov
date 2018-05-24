package ru.job4j.carStorage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.carStorage.models.Body;

import java.util.List;

public class BodyDAO {

    private Session session;

    private Transaction transaction;

    public static final BodyDAO instance = new BodyDAO();

    public static BodyDAO getInstance() {
        return instance;
    }

    public Session openCurrentSessionwithTransaction() {
        SessionFactory factory = getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeCurrentSessionwithTransaction() {
        transaction.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        return factory;
    }

    public Session getCurrentSession() {
        return session;
    }

    public List<Body> getAll() {
        List<Body> bodies = session.createQuery("from Body ").list();
        return bodies;
    }
}
