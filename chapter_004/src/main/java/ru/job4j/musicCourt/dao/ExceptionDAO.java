package ru.job4j.musicCourt.dao;

public class ExceptionDAO extends Exception {


    public ExceptionDAO() {
        super();
    }

    public ExceptionDAO(String message) {
        super(message);
    }

    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }
}
