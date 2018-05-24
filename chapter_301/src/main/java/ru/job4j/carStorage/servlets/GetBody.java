package ru.job4j.carStorage.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carStorage.DAO.BodyDAO;
import ru.job4j.carStorage.models.Body;
import ru.job4j.carStorage.services.BodyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetBody extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(GetBody.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BodyService bodyService = BodyService.getInstance();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        ObjectMapper mapper = new ObjectMapper();
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        PrintWriter writer = resp.getWriter();

        List<Body> bodies = bodyService.getAll();

        writer.append(
                ow.writeValueAsString(
                        bodies
                ));

        writer.flush();
        writer.flush();
    }
}
