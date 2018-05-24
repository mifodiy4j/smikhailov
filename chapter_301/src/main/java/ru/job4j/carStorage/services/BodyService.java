package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.BodyDAO;
import ru.job4j.carStorage.models.Body;

import java.util.List;

public class BodyService {

    private final BodyDAO bodyDAO = BodyDAO.getInstance();

    public static final BodyService instance = new BodyService();

    public static BodyService getInstance() {
        return instance;
    }

    public List<Body> getAll() {
        bodyDAO.openCurrentSessionwithTransaction();
        List<Body> bodies = bodyDAO.getAll();
        bodyDAO.openCurrentSessionwithTransaction();
        return bodies;
    }
}
