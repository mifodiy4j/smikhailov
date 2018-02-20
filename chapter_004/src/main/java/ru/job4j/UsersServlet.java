package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(EchoServlet.class);

    /**
     * Получает данные о пользователе
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));

        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        UserStore us = new UserStore();
        writer.append("users : " + us.selectById(id));

        writer.flush();
    }

    /**
     * Создает пользователя
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String parametrName = req.getParameter("name");
        String parametrLogin = req.getParameter("login");
        String parametrEmail = req.getParameter("email");
        String parametrCreateDate = req.getParameter("createDate");
        UserStore us = new UserStore();
        us.add(parametrName, parametrLogin, parametrEmail, parametrCreateDate);
        doGet(req, resp);
    }

    /**
     * Редактирует пользователя
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String parametrName = req.getParameter("name");
        String parametrLogin = req.getParameter("login");
        String parametrEmail = req.getParameter("email");
        String parametrCreateDate = req.getParameter("createDate");

        doGet(req, resp);
    }

    /**
     * Удаляет пользователя
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}

class User {
    private String name;
    private String login;
    private String email;
    private String createDate;

    public User(String name, String login, String email, String createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
