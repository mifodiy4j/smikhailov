package ru.job4j.kindCreateBean;

public class Square implements Figure {
    private final int facet;

    public Square(int facet) {
        this.facet = facet;
    }

    @Override
    public double getSquare() {
        return (facet*facet);
    }
}
