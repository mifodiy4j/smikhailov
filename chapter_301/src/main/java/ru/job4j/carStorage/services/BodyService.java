package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.BodyDAO;
import ru.job4j.carStorage.models.Body;

import java.util.List;

public class BodyService {

    private final BodyDAO bodyDAO = BodyDAO.getInstance();
    private static final BodyService instance = new BodyService();

    public static BodyService getInstance() {
        return instance;
    }

    public Body save(final Body body) {
        bodyDAO.save(body);
        return body;
    }

    public List<Body> getAll() {
        List<Body> bodies = bodyDAO.getAll();
        return bodies;
    }
}
