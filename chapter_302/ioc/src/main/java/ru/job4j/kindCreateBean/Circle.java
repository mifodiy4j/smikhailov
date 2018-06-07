package ru.job4j.kindCreateBean;

public class Circle implements Figure{
    private int radius;

    public Circle() {
        this.radius = 1;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double getSquare() {
        return Math.PI * Math.pow(radius, 2);
    }
}
