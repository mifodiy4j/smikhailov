package ru.job4j.carStorage.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carStorage.models.Engine;
import ru.job4j.carStorage.services.EngineService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetEngine extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(GetEngine.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EngineService engineService = EngineService.getInstance();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        PrintWriter writer = resp.getWriter();

        List<Engine> engines = engineService.getAll();

        writer.append(
                ow.writeValueAsString(
                        engines
                ));
        writer.flush();
    }
}
