package ru.job4j.kindCreateBean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class AreaFigureTest {

    @Test
    public void whenAddUserTOStorageShould() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Square square = context.getBean(Square.class);
        assertNotNull(square);
    }

    @Test
    public void whenAddUserTOStorage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Rectangle rectangle = context.getBean(Rectangle.class);
        assertNotNull(rectangle);
    }

    @Test
    public void whenAddUserTO() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Triangle triangle = context.getBean(Triangle.class);
        assertNotNull(triangle);
    }

    @Test
    public void whenAddUser() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Circle circle = context.getBean("getCircleWithRadiusOne", Circle.class);
        assertNotNull(circle);
    }
}