package ru.job4j.carStorage.models;

public class Transmission {

    private int id;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
