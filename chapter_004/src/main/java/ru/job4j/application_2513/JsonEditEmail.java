package ru.job4j.application_2513;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonEditEmail extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(JsonEditEmail.class);

    private UserStore userStore = UserStore.INSTANCE;;

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
        String newEmail = req.getParameter("email");
        UserStore userStore = UserStore.INSTANCE;

        userStore.updateEmailById(id, newEmail);

        //resp.sendRedirect(String.format("%s/user/role",req.getContextPath()));
    }
}