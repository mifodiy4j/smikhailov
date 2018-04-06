package ru.job4j.application_2513;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JsonController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        UserStore userStore = UserStore.INSTANCE;
        List<Integer> list = userStore.getListId();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PrintWriter printWriter = resp.getWriter();

        JSONObject jsonObject = new JSONObject();
        for(int i : list) {
            jsonObject.put("id", String.valueOf(i));
            jsonObject.put("name", userStore.selectById(i).getName());
        }
        printWriter.print(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserStore userStore = UserStore.INSTANCE;

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        PrintWriter printWriter = resp.getWriter();

        JSONObject jsonObject = new JSONObject();

        if (userStore.isCredentional(login, password)) {

            int roleId = userStore.selectRoleIdByLogin(login);

            jsonObject.put("role", userStore.selectRoleByRoleId(roleId));
            jsonObject.put("id", userStore.selectIdByLogin(login));

        } else {
            jsonObject.put("role", "error");
        }
        printWriter.print(jsonObject.toString());
    }
}
