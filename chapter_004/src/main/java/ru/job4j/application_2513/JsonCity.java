package ru.job4j.application_2513;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        List<City> listC = new ArrayList<>();
        for(String city : listCity) {
            listC.add(new City(city));
        }

        writer.append(ow.writeValueAsString(listC));
        writer.flush();
    }
}