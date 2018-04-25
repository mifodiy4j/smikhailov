package ru.job4j.musicCourt.dao;

import ru.job4j.musicCourt.domain.Model;

import java.util.List;

public interface RoleRepository<T extends Model> {

    List<Model> getFullEntity(Specification specification) throws ExceptionDAO;
}
