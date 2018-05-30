package ru.job4j.kindCreateBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Rectangle implements Figure {
    private int a;
    private int b;

    @Autowired
    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getSquare() {
        return (a * b);
    }
}
