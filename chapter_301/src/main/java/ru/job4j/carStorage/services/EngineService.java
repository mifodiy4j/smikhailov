package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.EngineDAO;
import ru.job4j.carStorage.models.Engine;

import java.util.List;

public class EngineService {

    private final EngineDAO engineDAO = EngineDAO.getInstance();

    public static final EngineService instance = new EngineService();

    public static EngineService getInstance() {
        return instance;
    }

    public Engine save(final Engine engine) {
        engineDAO.openCurrentSessionwithTransaction();
        engineDAO.save(engine);
        engineDAO.closeCurrentSessionwithTransaction();
        return engine;
    }

    public List<Engine> getAll() {
        engineDAO.openCurrentSessionwithTransaction();
        List<Engine> engines = engineDAO.getAll();
        engineDAO.closeCurrentSessionwithTransaction();
        return engines;
    }
}
