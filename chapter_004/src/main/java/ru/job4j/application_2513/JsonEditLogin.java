package ru.job4j.application_2513;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JsonEditLogin extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(JsonEditLogin.class);

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
        String newLogin = req.getParameter("login");
        UserStore userStore = UserStore.INSTANCE;

        boolean validLogin = true;
        List<Integer> listId = userStore.getListId();
        for (int idFromBD : listId) {
            if (newLogin.equals(userStore.selectById(idFromBD).getLogin())) {
                validLogin = false;
            }
        }

        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        writer.append("[");
        writer.append("{");
        writer.append("\"result\":\"");

        if (validLogin) {
            userStore.updateLoginById(id, newLogin);
            writer.append("change");
        } else {
            writer.append("no valid");
        }
        writer.append("\"");
        writer.append("}");
        writer.append("]");
        writer.flush();
    }
}