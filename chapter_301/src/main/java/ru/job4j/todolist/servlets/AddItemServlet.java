package ru.job4j.todolist.servlets;

import ru.job4j.todolist.DAO.ItemDAO;
import ru.job4j.todolist.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class AddItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Item item = new Item();
        item.setDescription(req.getParameter("description"));
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);

        ItemDAO itemDAO = new ItemDAO();
        itemDAO.save(item);
    }
}
