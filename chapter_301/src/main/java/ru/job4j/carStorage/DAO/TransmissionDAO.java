package ru.job4j.carStorage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        return HibernateFactory.getFactory();
    }

    public Session getCurrentSession() {
        return session;
    }

    public void save(Transmission transmission){
        getCurrentSession().save(transmission);
    }

    public List<Transmission> getAll() {
        List<Transmission> transmissions = session.createQuery("from Transmission ").list();
        return transmissions;
    }

}
