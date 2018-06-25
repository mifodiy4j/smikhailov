package ru.job4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getAll() {
        return (List<Person>) this.repository.findAll();
    }

    public Person add(Person person) {
        return this.repository.save(person);
    }
}
