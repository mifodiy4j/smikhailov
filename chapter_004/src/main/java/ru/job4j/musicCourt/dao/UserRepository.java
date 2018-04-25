package ru.job4j.musicCourt.dao;

import ru.job4j.musicCourt.domain.Model;

import java.util.List;

public interface UserRepository<T extends Model> {

    List<Model> getFullEntity(Specification specification) throws ExceptionDAO;

    void insert(Specification specification) throws ExceptionDAO;

    T getByParameter(Specification specification) throws ExceptionDAO;
}
