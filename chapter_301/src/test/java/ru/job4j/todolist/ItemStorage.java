package ru.job4j.todolist.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.todolist.models.Item;

import java.sql.Timestamp;
import java.util.List;

public class ItemStorage {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        /*Item item = new Item();
        item.setDescription("description_3");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);

        session.save(item);*/

        /*user.setId(1);
        user.setLogin("test_1");
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        session.update(user);*/

       /* user.setId(1);
        session.delete(user);*/

        List<Item> items = session.createQuery("from Item ").list();
        for (Item item : items) {
            System.out.println(item.getId() + item.getDescription() + item.getCreated() + item.getDone());
        }

        session.getTransaction().commit();
        session.close();
        factory.close();
    }

}
