package ru.job4j.musicCourt.servlets;

import org.json.JSONObject;
import ru.job4j.musicCourt.dao.ExceptionDAO;
import ru.job4j.musicCourt.dao.implement.UserImpl;
import ru.job4j.musicCourt.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Authentication extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UserImpl userImpl = new UserImpl();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PrintWriter printWriter = resp.getWriter();

        JSONObject jsonObject = new JSONObject();

        if (userImpl.isCredentional(name, password)) {
            List<User> users = new ArrayList<>();
            try {
                users = userImpl.getAll();
            } catch (ExceptionDAO exceptionDAO) {
                exceptionDAO.printStackTrace();
            }

            for (User user : users) {
                if (name.equals(user.getName())) {

                    jsonObject.put("role", user.getRole().getRole());
                    jsonObject.put("id", user.getId());

                    break;
                }
            }

        } else {
            jsonObject.put("role", "error");
        }
        printWriter.print(jsonObject.toString());
    }
}
