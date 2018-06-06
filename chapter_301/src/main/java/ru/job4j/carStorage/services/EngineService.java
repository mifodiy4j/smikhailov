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
        engineDAO.save(engine);
        return engine;
    }

    public List<Engine> getAll() {
        List<Engine> engines = engineDAO.getAll();
        return engines;
    }
}
