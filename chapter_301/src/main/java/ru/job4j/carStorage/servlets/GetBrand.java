package ru.job4j.carStorage.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carStorage.services.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetBrand extends HttpServlet {

    private static final Logger Log = LoggerFactory.getLogger(GetBrand.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CarService carService = CarService.getInstance();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        PrintWriter writer = resp.getWriter();

        List<String> brands = carService.getListBrand();
        int count = 0;

        writer.append("[");
        for(String brand : brands) {
            count++;
            writer.append("{");
            writer.append("\"name\":\"");
            writer.append(brand);
            writer.append("\"");
            writer.append("}");
            if (count != brands.size()) {
                writer.append(",");
            }
        }
        writer.append("]");
        writer.flush();
    }
}
