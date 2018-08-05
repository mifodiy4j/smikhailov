package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Class PatterTemplate
 *
 * @author SERG
 * @since 29.06.2018
 */
public class PatterTemplate {

    private SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    /**
     * Шаблон Template для Hibernate
     *
     * @param actions функция, которая принимает один аргумент Session
     * @return результат в зависимости от запроса action
     */
    public <T> T tx(final Function<Session, T> actions) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return actions.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    /**
     * создания модели
     */
    public User create(User newUser) {
        return tx(session -> {
                    Query query = session.createQuery("insert into User (name)");
                    int result = query.executeUpdate();
                    return newUser;
                }

        );
    }

    /**
     * получение значений по условию
     */
    public Collection<User> getByCondition(final String findName) {
        return tx(session -> {
                    Query query = session.createQuery("from User where name = :value");
                    query.setParameter("value", findName);
                    List<User> users = query.list();
                    return users;
                }
        );
    }

    /**
     * получания списка
     *
     * @return Collection<User> все элементы таблицы
     */
    public Collection<User> getAll() {
        return tx(session -> session.createQuery("from User ").list()
        );
    }

    /**
     * обновления
     */
    public User update(User updateUser) {
        return tx(session -> {
                    session.createQuery("from User ");
                    return updateUser;
                }
        );
    }

    /**
     * удаления
     */
    public int delete(int id) {
        return tx(session -> {
                    Query query = session.createQuery("delete from User where id = :value");
                    query.setParameter("value", id);
                    return id;
                }
        );
    }
}