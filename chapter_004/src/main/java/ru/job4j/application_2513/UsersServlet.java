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

public class UsersServlet extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(UsersServlet.class);

    private UserStore userStore = UserStore.INSTANCE;

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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        User user;
        List<Integer> listId = this.userStore.getListId();

        StringBuilder sb = new StringBuilder("<table border='1'>");
        sb.append("<tr>" +
                "<th>" + "ID" + "</th>" +
                "<th>" + "Name" + "</th>" +
                "<th>" + "Login" + "</th>" +
                "<th>" + "Email" + "</th>" +
                "<th>" + "Create Date" + "</th>" +
                "</tr>");

        for (int id : listId) {
            user = this.userStore.selectById(id);
            sb.append("<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + user.getName() + "</td>" +
                    "<td>" + user.getLogin() + "</td>" +
                    "<td>" + user.getEmail() + "</td>" +
                    "<td>" + user.getCreateDate() + "</td>" +
                    "</tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title></title>" +
                "</head>" +
                "<body>" +

                "<form action='"+req.getContextPath()+"/useradd' method='post'>" +
                "name : <input type=text' name='name'/>" +
                "<br/>" +
                "login : <input type=text' name='login'/>" +
                "<br/>" +
                "email : <input type=text' name='email'/>" +
                "<br/>" +
                "Create date (yyyy-MM-dd) : <input type=text' name='createDate'/>" +
                "<br/>" +
                "<input type='submit' value='Add new users'>" +
                "</form>" +

                "<br/>" +

                "<form action='"+req.getContextPath()+"/useredit' method='post'>" +
                "id : <input type=text' name='id'/>" +
                "<br/>" +
                "name : <input type=text' name='name'/>" +
                "<br/>" +
                "<input type='submit' value='Edit'>" +
                "</form>" +

                "<br/>" +

                "<form action='"+req.getContextPath()+"/userdelete' method='post'>" +
                "id : <input type=text' name='id'/>" +
                "<br/>" +
                "<input type='submit' value='Delete'>" +
                "</form>" +

                "<br/>" +
                sb.toString() +
                "</body>" +
                "</html>");
        writer.flush();
    }
}