package ru.job4j.carStorage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.carStorage.models.User;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    private Session session;

    private Transaction transaction;

    public static final UserDAO instance = new UserDAO();

    public UserDAO() {
    }

    public static UserDAO getInstance() {
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

    public void save(User user){
        getCurrentSession().save(user);
    }

    public List<User> getAll() {
        List<User> users = session.createQuery("from User ").list();
        return users;
    }

    public Optional<User> findById(final int id){
        Optional<User> result = Optional.empty();
        User user = getCurrentSession().get(User.class, id);
        if (user != null) {
            result = Optional.of(user);
        }
        return result;
    }
}
