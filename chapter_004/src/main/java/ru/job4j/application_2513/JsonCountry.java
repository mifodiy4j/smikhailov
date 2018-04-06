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

public class JsonCountry extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(JsonCountry.class);

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

        List<String> listCountry = userStore.getListCountry();

        PrintWriter writer = resp.getWriter();
        int count = 0;

        writer.append("[");
        for(String country : listCountry) {
            count++;
            writer.append("{");
            writer.append("\"country\":\"");
            writer.append(country);
            writer.append("\"");
            writer.append("}");
            if (count != listCountry.size()) {
                writer.append(",");
            }
        }
        writer.append("]");
        writer.flush();
    }
}