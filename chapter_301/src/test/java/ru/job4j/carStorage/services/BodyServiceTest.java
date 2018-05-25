package ru.job4j.carStorage.services;

import org.junit.Test;
import ru.job4j.carStorage.models.Body;

import static org.junit.Assert.*;

public class BodyServiceTest {

    private final BodyService bodies = BodyService.getInstance();

    @Test
    public void testGetAll() throws Exception {
        Body body = new Body();
        body.setDesc("test");
        this.bodies.save(body);
        assertTrue(this.bodies.getAll().contains(body));
    }
}