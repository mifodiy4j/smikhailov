package ru.job4j.application_2513;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JsonAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/json");

        UserStore userStore = UserStore.INSTANCE;
        List<Integer> list = userStore.getListId();


        PrintWriter writer = resp.getWriter();
        int count = 0;

        writer.append("[");
        for(int i : list) {
            count++;
            writer.append("{");
            writer.append("\"id\":\"");
            writer.append(String.valueOf(i));
            writer.append("\"");
            writer.append(",");
            writer.append("\"name\":\"");
            writer.append(userStore.selectById(i).getName());
            writer.append("\"");
            writer.append(",");
            writer.append("\"login\":\"");
            writer.append(userStore.selectById(i).getLogin());
            writer.append("\"");
            writer.append(",");
            writer.append("\"email\":\"");
            writer.append(userStore.selectById(i).getEmail());
            writer.append("\"");
            writer.append(",");
            writer.append("\"createDate\":\"");
            writer.append(userStore.selectById(i).getCreateDate());
            writer.append("\"");
            writer.append(",");
            writer.append("\"role\":\"");
            writer.append(userStore.selectById(i).getRole());
            writer.append("\"");
            writer.append(",");
            writer.append("\"password\":\"");
            writer.append(userStore.selectById(i).getPassword());
            writer.append("\"");
            writer.append("}");
            if (count != list.size()) {
                writer.append(",");
            }
        }
        writer.append("]");
        writer.flush();
    }
}
