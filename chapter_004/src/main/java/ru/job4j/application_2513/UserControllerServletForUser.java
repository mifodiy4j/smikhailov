package ru.job4j.application_2513;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserControllerServletForUser extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(UserControllerServletForUser.class);

    /**
     * Получает данные о пользователе
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore userStore = UserStore.INSTANCE;
        req.setAttribute("users", userStore);
        req.setAttribute("listId", userStore.getListId());

        req.getRequestDispatcher("/WEB-INF/views/UserViewForUser.jsp").forward(req, resp);
    }
}