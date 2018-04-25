package ru.job4j.musicCourt.dao;

public interface SqlSpecification extends Specification {
    String toSqlQuery();
}
