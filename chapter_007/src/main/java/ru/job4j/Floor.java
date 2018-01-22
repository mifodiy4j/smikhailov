package ru.job4j;

import java.util.Comparator;

public class Floor{
    private int number;
    private int prioritet;

    public Floor(int number, int prioritet) {
        this.number = number;
        this.prioritet = prioritet;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number) + " - " + String.valueOf(prioritet);
    }
}

class FloorComparator implements Comparator<Floor> {
    @Override
    public int compare(Floor f1, Floor f2) {
        return (f1.getPrioritet() - f2.getPrioritet());
    }
}
