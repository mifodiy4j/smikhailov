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

    private static final Logger Log = LoggerFactory.getLogger(UsersServlet.class);

    private final UserStore users = UserStore.getInstance();

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

        writer.append("users : " + users.selectById(id));
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

        users.add(parametrName, parametrLogin, parametrEmail, parametrCreateDate);
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
        String parametrId = req.getParameter("id");
        int id = Integer.parseInt(parametrId);
        String parametrNewName = req.getParameter("name");

        users.updateNameById(id, parametrNewName);

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("users : " + users.selectById(id));
        writer.flush();
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
        resp.setContentType("text/html");
        String parametrId = req.getParameter("id");
        int id = Integer.parseInt(parametrId);

        users.deleteById(id);
    }
}

class User {
    private String name;
    private String login;
    private String email;
    private String createDate;

    public User() {
    }

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
        return "{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
