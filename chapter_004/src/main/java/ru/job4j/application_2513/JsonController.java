package ru.job4j.application_2513;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        if (userStore.isCredentional(login, password)) {

            int roleId = userStore.selectRoleIdByLogin(login);
            rootNode.put("role", userStore.selectRoleByRoleId(roleId));
            rootNode.put("id", userStore.selectIdByLogin(login));
        } else {
            rootNode.put("role", "error");
        }
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        printWriter.print(jsonString);

    }
}
