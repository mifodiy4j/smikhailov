package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.models.Body;

@Repository
public interface BodyRepository extends CrudRepository<Body, Integer> {
}
