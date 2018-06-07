package ru.job4j.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ImportUserTest {

    @Test
    public void whenGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-test.xml");
        ImportUser importUser = (ImportUser) context.getBean("import");

        assertNotNull(importUser);
    }

    @Test
    public void whenAddOneUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-test.xml");
        ImportUser importUser = (ImportUser) context.getBean("import");

        importUser.add(new User("Jack"));

        assertThat(importUser.getAll().get(0).getName(), is("Jack"));
    }

    @Test
    public void whenAddAndDeleteUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-test.xml");
        ImportUser importUser = (ImportUser) context.getBean("import");

        User jack = new User("Jack");
        importUser.add(jack);
        importUser.add(new User("Bob"));

        importUser.delete(jack);

        assertThat(importUser.getAll().get(0).getName(), is("Bob"));
    }
}