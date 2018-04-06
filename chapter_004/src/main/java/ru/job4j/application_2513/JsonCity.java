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

public class JsonCity extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(JsonCity.class);

    /**
     * Создает пользователя
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        UserStore userStore = UserStore.INSTANCE;

        List<String> listCity = userStore.getListCity();

        PrintWriter writer = resp.getWriter();
        int count = 0;

        writer.append("[");
        for(String city : listCity) {
            count++;
            writer.append("{");
            writer.append("\"city\":\"");
            writer.append(city);
            writer.append("\"");
            writer.append("}");
            if (count != listCity.size()) {
                writer.append(",");
            }
        }
        writer.append("]");
        writer.flush();
    }
}