package ru.job4j.musicCourt.servlets;

import org.json.JSONObject;
import ru.job4j.musicCourt.dao.AddUserSqlSpecification;
import ru.job4j.musicCourt.dao.ExceptionDAO;
import ru.job4j.musicCourt.dao.SqlSpecification;
import ru.job4j.musicCourt.dao.implement.AddressImpl;
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

public class AddNewUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User();
        int id = Integer.parseInt(req.getParameter("id"));
        user.setId(id);
        user.setName(req.getParameter("name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        user.setPassword(req.getParameter("password"));

        Address address = new Address();
        address.setId(id);
        address.setCountry(req.getParameter("country"));
        address.setCity(req.getParameter("city"));
        address.setStreet(req.getParameter("street"));
        address.setHouse(Integer.parseInt(req.getParameter("house")));

        int roleId = Integer.parseInt(req.getParameter("role"));
        Role role = new Role(roleId);

        int musicTypeId = Integer.parseInt(req.getParameter("musicType"));
        MusicType musicType = new MusicType(musicTypeId);

        UserImpl userImpl = new UserImpl();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PrintWriter printWriter = resp.getWriter();

        JSONObject jsonObject = new JSONObject();

        SqlSpecification sqlSpecification = new AddUserSqlSpecification(user, address, role, musicType);

        try {
            userImpl.insert(sqlSpecification);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        printWriter.print(jsonObject.toString());
    }
}
