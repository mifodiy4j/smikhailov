package ru.job4j.application_2513;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        //resp.setContentType("application/json");

        String stringId = req.getParameter("id");
        int id = Integer.parseInt(stringId);

        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        UserStore userStore = UserStore.INSTANCE;
        User user = userStore.selectById(id);

        writer.append("[");
        writer.append("{");
        writer.append("\"name\":\"");
        writer.append(user.getName());
        writer.append("\"");
        writer.append(",");
        writer.append("\"login\":\"");
        writer.append(user.getLogin());
        writer.append("\",");
        writer.append("\"email\":\"");
        writer.append(user.getEmail());
        writer.append("\",");
        writer.append("\"createDate\":\"");
        writer.append(user.getCreateDate());
        writer.append("\",");
        writer.append("\"password\":\"");
        writer.append(user.getPassword());
        writer.append("\",");
        writer.append("\"role\":\"");
        writer.append(user.getRole());
        writer.append("\",");
        writer.append("\"country\":\"");
        writer.append(user.getCountry());
        writer.append("\",");
        writer.append("\"city\":\"");
        writer.append(user.getCity());
        writer.append("\"");
        writer.append("}");
        writer.append("]");

        writer.flush();
    }

}
