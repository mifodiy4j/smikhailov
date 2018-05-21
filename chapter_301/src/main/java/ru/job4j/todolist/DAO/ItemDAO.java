package ru.job4j.todolist.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.todolist.models.Item;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class ItemDAO {

    SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();
    Session session = factory.openSession();

    public void save(Item item) {
        session.beginTransaction();

        session.save(item);

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    public Collection<Item> values() {
        return tx(session -> session.createQuery("from Item ").list()
        );
    }

    public Collection<Item> findByStatusDone(final boolean status) {

        return tx(session -> {
                    Query query = session.createQuery("from Item where done = :code");
                    query.setParameter("code", status);
                    List<Item> items = query.list();
                    return items;
                }
        );
    }

    public <T>T tx(final Function<Session,T> command) {
        session.beginTransaction();
        try{
            return command.apply(session);
        } catch (final Exception e){
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
            factory.close();
        }
    }
}