package ru.job4j.storage.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.storage.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

public class UserDAO {

    public static final UserDAO instance = new UserDAO();

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        return instance;
    }

    SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    public void save(User user){
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(User user){
        Session session = factory.openSession();
        session.beginTransaction();

        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    public Collection<User> getAll() {
        return tx(session -> session.createQuery("from User ").list()
        );
    }

    public Optional<User> findById(final int id){
        return tx(session -> {
            Optional<User> result = Optional.empty();
            User user = session.get(User.class, id);
            if (user != null) {
                result = Optional.of(user);
            }
            return result;
        });
    }

    public <T>T tx(final Function<Session,T> command) {
        Session session = factory.openSession();
        session.beginTransaction();
        try{
            return command.apply(session);
        } catch (final Exception e){
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}