package ru.job4j.carStorage.services;

import ru.job4j.carStorage.DAO.TransmissionDAO;
import ru.job4j.carStorage.models.Transmission;

import java.util.List;

public class TransmissionService {

    private final TransmissionDAO transmissionDAO = TransmissionDAO.getInstance();

    public static final TransmissionService instance = new TransmissionService();

    public static TransmissionService getInstance() {
        return instance;
    }

    public Transmission save(final Transmission transmission) {
        transmissionDAO.openCurrentSessionwithTransaction();
        transmissionDAO.save(transmission);
        transmissionDAO.closeCurrentSessionwithTransaction();
        return transmission;
    }

    public List<Transmission> getAll() {
        transmissionDAO.openCurrentSessionwithTransaction();
        List<Transmission> transmissions = transmissionDAO.getAll();
        transmissionDAO.closeCurrentSessionwithTransaction();
        return transmissions;
    }
}
