package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.models.Body;

import java.util.List;

public class BodyDAO {

    SessionFactory factory = HibernateFactory.getFactory();
    Session session = factory.openSession();

    public static final BodyDAO instance = new BodyDAO();

    public static BodyDAO getInstance() {
        return instance;
    }

    public void save(Body body){
        session.beginTransaction();
        session.save(body);
        session.getTransaction().commit();
        session.close();
    }

    public List<Body> getAll() {
        session.beginTransaction();
        List<Body> bodies = session.createQuery("from Body ").list();
        session.getTransaction().commit();
        session.close();
        return bodies;
    }
}
