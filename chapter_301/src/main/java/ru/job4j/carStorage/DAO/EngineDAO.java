package ru.job4j.carStorage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.carStorage.models.Engine;

import java.util.List;

public class EngineDAO {

    SessionFactory factory = HibernateFactory.getFactory();

    public static final EngineDAO instance = new EngineDAO();

    public static EngineDAO getInstance() {
        return instance;
    }

    public void save(Engine engine){
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(engine);
        session.getTransaction().commit();
        session.close();
    }

    public List<Engine> getAll() {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Engine> engines = session.createQuery("from Engine ").list();
        session.getTransaction().commit();
        session.close();
        return engines;
    }
}
