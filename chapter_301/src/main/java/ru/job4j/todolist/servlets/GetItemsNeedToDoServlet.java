package ru.job4j.todolist.servlets;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.models.Item;

import java.util.Collection;
import java.util.List;

public class GetItemsNeedToDoServlet extends WrapperServlet {

    private static final Logger Log = LoggerFactory.getLogger(GetItemsNeedToDoServlet.class);

    public Collection<Item> values() {

        return tx(session -> {
                    Query query = session.createQuery("from Item where done = :code");
                    query.setParameter("code", false);
                    List<Item> items = query.list();
                    return items;
                }
        );
    }
}
