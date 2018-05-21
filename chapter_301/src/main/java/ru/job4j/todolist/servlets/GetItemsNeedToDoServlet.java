package ru.job4j.todolist.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.DAO.ItemDAO;
import ru.job4j.todolist.models.Item;

import java.util.Collection;

public class GetItemsNeedToDoServlet extends WrapperServlet {

    private static final Logger Log = LoggerFactory.getLogger(GetItemsNeedToDoServlet.class);

    @Override
    Collection<Item> getItems() {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO.findByStatusDone(false);
    }
}
