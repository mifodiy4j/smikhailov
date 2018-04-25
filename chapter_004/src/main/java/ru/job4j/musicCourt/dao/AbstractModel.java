package ru.job4j.musicCourt.dao;

import ru.job4j.musicCourt.domain.Model;

import java.util.List;

public interface AbstractModel<T extends Model> {

    void insert(T model) throws ExceptionDAO;

    void update(T model) throws ExceptionDAO;

    void delete(int id) throws ExceptionDAO;

    T getById(int id) throws ExceptionDAO;

    List<T> getAll() throws ExceptionDAO;
}
