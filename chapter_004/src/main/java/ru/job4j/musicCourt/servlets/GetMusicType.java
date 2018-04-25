package ru.job4j.musicCourt.servlets;

import ru.job4j.musicCourt.dao.ExceptionDAO;
import ru.job4j.musicCourt.dao.implement.MusicTypeImpl;
import ru.job4j.musicCourt.domain.MusicType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetMusicType extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        MusicTypeImpl musicTypeImpl = new MusicTypeImpl();

        List<MusicType> listMusicType = null;
        try {
            listMusicType = musicTypeImpl.getAll();
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        PrintWriter writer = resp.getWriter();
        int count = 0;

        writer.append("[");
        for(MusicType musicType : listMusicType) {
            count++;
            writer.append("{");
            writer.append("\"id\":\"");
            writer.append(String.valueOf(musicType.getId()));
            writer.append("\"");
            writer.append(",");
            writer.append("\"musicType\":\"");
            writer.append(musicType.getMusicType());
            writer.append("\"");
            writer.append("}");
            if (count != listMusicType.size()) {
                writer.append(",");
            }
        }
        writer.append("]");
        writer.flush();
    }
}
