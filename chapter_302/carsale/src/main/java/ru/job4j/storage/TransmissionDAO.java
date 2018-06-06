package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.models.Transmission;
import java.util.List;

public class TransmissionDAO {

    SessionFactory factory = HibernateFactory.getFactory();
    Session session = factory.openSession();

    public static final TransmissionDAO instance = new TransmissionDAO();

    public static TransmissionDAO getInstance() {
        return instance;
    }

    public void save(Transmission transmission){
        session.beginTransaction();
        session.save(transmission);
        session.getTransaction().commit();
        session.close();
    }

    public List<Transmission> getAll() {
        session.beginTransaction();
        List<Transmission> transmissions = session.createQuery("from Transmission ").list();
        session.getTransaction().commit();
        session.close();
        return transmissions;
    }

}
