package ru.job4j.musicCourt.servlets;

import ru.job4j.musicCourt.dao.ExceptionDAO;
import ru.job4j.musicCourt.dao.implement.UserImpl;
import ru.job4j.musicCourt.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetFullAboutUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");

        String stringId = req.getParameter("id");
        int id = Integer.parseInt(stringId);

        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        UserImpl userImpl = new UserImpl();

        User user = null;
        try {
            user = userImpl.getById(id);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        writer.append("[");
        writer.append("{");
        writer.append("\"id\":\"");
        writer.append(String.valueOf(user.getId()));
        writer.append("\"");
        writer.append(",");
        writer.append("\"name\":\"");
        writer.append(user.getName());
        writer.append("\",");
        writer.append("\"age\":\"");
        writer.append(String.valueOf(user.getAge()));
        writer.append("\",");
        writer.append("\"country\":\"");
        writer.append(user.getAdress().getCountry());
        writer.append("\",");
        writer.append("\"city\":\"");
        writer.append(user.getAdress().getCity());
        writer.append("\",");
        writer.append("\"street\":\"");
        writer.append(user.getAdress().getStreet());
        writer.append("\",");
        writer.append("\"house\":\"");
        writer.append(String.valueOf(user.getAdress().getHouse()));
        writer.append("\",");
        writer.append("\"role\":\"");
        writer.append(user.getRole().getRole());
        writer.append("\",");
        writer.append("\"musicType\":\"");
        writer.append(user.getMusicType().getMusicType());
        writer.append("\"");
        writer.append(",");
        writer.append("\"password\":\"");
        writer.append(user.getPassword());
        writer.append("\"");
        writer.append("}");
        writer.append("]");

        writer.flush();
    }

}
