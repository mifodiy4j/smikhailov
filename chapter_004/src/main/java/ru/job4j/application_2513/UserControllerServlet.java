package ru.job4j.application_2513;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserControllerServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(UserControllerServlet.class);

    /**
     * Получает данные о пользователе
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", new UserStore());
        req.setAttribute("listId", new UserStore().getListId());
        req.getRequestDispatcher("/WEB-INF/views/UserView.jsp").forward(req, resp);
    }
}