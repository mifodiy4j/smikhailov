package ru.job4j.kindCreateBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Triangle getTriangle() {
        return new Triangle();
    }

    @Bean
    public Circle getCircleWithRadiusOne() {
        return new Circle();
    }
}
