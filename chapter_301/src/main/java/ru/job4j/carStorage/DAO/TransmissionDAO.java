package ru.job4j.carStorage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.carStorage.models.Transmission;

import java.util.List;

public class TransmissionDAO {

    private Session session;

    private Transaction transaction;

    public static final TransmissionDAO instance = new TransmissionDAO();

    public static TransmissionDAO getInstance() {
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

    public List<Transmission> getAll() {
        List<Transmission> transmissions = session.createQuery("from Transmission ").list();
        return transmissions;
    }

}
