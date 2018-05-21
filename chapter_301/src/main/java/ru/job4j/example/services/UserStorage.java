package ru.job4j.example.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.example.models.Comment;
import ru.job4j.example.models.Item;
import ru.job4j.example.models.User;

import java.sql.Timestamp;
import java.util.List;

public class UserStorage {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        /*Item item = new Item();
        item.setDesc("test");
        item.setAuthor(new User(7));
        item.setCreated(new Timestamp(System.currentTimeMillis()));

        session.save(item);*/

        Item item = session.get(Item.class, 1);
        System.out.println(item);

        /*Comment comment = new Comment();
        comment.setDesc("test");
        comment.setSpent(1);
        comment.setItem(new Item(1));
        session.save(comment);*/

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
