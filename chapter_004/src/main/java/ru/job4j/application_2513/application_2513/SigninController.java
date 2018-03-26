package ru.job4j.application_2513;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SigninController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginViewApplication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (UserStore.getInstance().isCredentional(login, password)) {
            HttpSession session = req.getSession();

            int roleId = UserStore.getInstance().selectRoleIdByLogin(login);

            synchronized (session) {
                session.setAttribute("login", login);
                session.setAttribute("role", UserStore.getInstance().selectRoleByRoleId(roleId));
                session.setAttribute("id", UserStore.getInstance().selectIdByLogin(login));
                resp.sendRedirect(String.format("%s/user/role", req.getContextPath()));
            }
        } else {
            req.setAttribute("error", String.format("Error in application%nCredentional invalid"));
            doGet(req, resp);
        }
    }
}
