package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.models.Body;

import java.util.List;

public class BodyDAO {

    private SessionFactory factory = HibernateFactory.getFactory();
    private static final BodyDAO instance = new BodyDAO();

    public static BodyDAO getInstance() {
        return instance;
    }

    public void save(Body body){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(body);
        session.getTransaction().commit();
        session.close();
    }

    public List<Body> getAll() {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Body> bodies = session.createQuery("from Body ").list();
        session.getTransaction().commit();
        session.close();
        return bodies;
    }
}
