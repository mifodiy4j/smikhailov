package ru.job4j.carStorage.services;

import org.junit.Test;
import ru.job4j.carStorage.models.Engine;

import static org.junit.Assert.*;

public class EngineServiceTest {

    private final EngineService engines = EngineService.getInstance();

    @Test
    public void testGetAll() throws Exception {
        Engine engine = new Engine();
        engine.setDesc("test");
        this.engines.save(engine);
        assertTrue(this.engines.getAll().contains(engine));
    }
}