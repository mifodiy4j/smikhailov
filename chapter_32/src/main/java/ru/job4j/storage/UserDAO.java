package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.models.User;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {

    SessionFactory factory = HibernateFactory.getFactory();
    Session session = factory.openSession();

    public static final UserDAO instance = new UserDAO();

    public UserDAO() {
    }

    public static UserDAO getInstance() {
        return instance;
    }

    public void save(User user){
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public List<User> getAll() {
        session.beginTransaction();
        List<User> users = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    public Optional<User> findById(final int id){
        session.beginTransaction();
        Optional<User> result = Optional.empty();
        User user = session.get(User.class, id);
        if (user != null) {
            result = Optional.of(user);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
