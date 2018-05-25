package ru.job4j.carStorage.services;

import org.junit.Test;
import ru.job4j.carStorage.models.Transmission;

import static org.junit.Assert.*;

public class TransmissionServiceTest {

    private final TransmissionService transmissions = TransmissionService.getInstance();

    @Test
    public void testGetAll() throws Exception {
        Transmission transmission = new Transmission();
        transmission.setDesc("test");
        this.transmissions.save(transmission);
        assertTrue(this.transmissions.getAll().contains(transmission));
    }
}