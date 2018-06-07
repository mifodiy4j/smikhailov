package ru.job4j.kindCreateBean;

public class Triangle implements Figure {
    private int a;
    private int b;
    private int c;

    public Triangle() {
        this.a = 1;
        this.b = 1;
        this.c = 1;
    }

    @Override
    public double getSquare() {
        double p = (a + b + c)/2;
        double s = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        return s;
    }
}
