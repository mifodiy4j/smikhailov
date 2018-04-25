package ru.job4j.musicCourt.servlets;

import org.json.JSONObject;
import ru.job4j.musicCourt.dao.ExceptionDAO;
import ru.job4j.musicCourt.dao.FindUserSqlSpecification;
import ru.job4j.musicCourt.dao.SqlSpecification;
import ru.job4j.musicCourt.dao.implement.MusicTypeImpl;
import ru.job4j.musicCourt.dao.implement.RoleImpl;
import ru.job4j.musicCourt.dao.implement.UserImpl;
import ru.job4j.musicCourt.domain.Address;
import ru.job4j.musicCourt.domain.MusicType;
import ru.job4j.musicCourt.domain.Role;
import ru.job4j.musicCourt.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FindUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Address address = new Address();
        address.setCountry(req.getParameter("country"));
        address.setCity(req.getParameter("city"));
        address.setStreet(req.getParameter("street"));
        address.setHouse(Integer.parseInt(req.getParameter("house")));

        UserImpl userImpl = new UserImpl();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PrintWriter printWriter = resp.getWriter();

        JSONObject jsonObject = new JSONObject();

        SqlSpecification sqlSpecification = new FindUserSqlSpecification(address);

        User user = null;
        Role role = null;
        MusicType musicType = null;

        try {
            user = userImpl.getByParameter(sqlSpecification);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        try {
            role = new RoleImpl().getById(user.getRole().getId());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        try {
            musicType = new MusicTypeImpl().getById(user.getMusicType().getId());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        jsonObject.put("id", user.getId());
        jsonObject.put("name", user.getName());
        jsonObject.put("age", user.getAge());
        jsonObject.put("role", role.getRole());
        jsonObject.put("musicType", musicType.getMusicType());
        jsonObject.put("password", user.getPassword());

        printWriter.print(jsonObject.toString());
    }
}
