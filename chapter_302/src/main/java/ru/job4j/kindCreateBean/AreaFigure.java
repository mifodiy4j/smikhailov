package ru.job4j.kindCreateBean;

public class AreaFigure {
    private Figure figure;

    public AreaFigure(Figure figure) {
        this.figure = figure;
    }

    public double getSquare() {
        return this.figure.getSquare();
    }
}
