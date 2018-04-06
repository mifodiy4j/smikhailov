package ru.job4j.application_2513;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonEditRole extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(JsonEditRole.class);

    /**
     * Создает пользователя
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String stringId = req.getParameter("id");
        int id = Integer.parseInt(stringId);
        String stringNewRole = req.getParameter("role");
        int newRole = Integer.parseInt(stringNewRole);
        UserStore userStore = UserStore.INSTANCE;

        userStore.updateRole(id, newRole);
    }
}