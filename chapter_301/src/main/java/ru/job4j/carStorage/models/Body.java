package ru.job4j.carStorage.models;

public class Body {

    private int id;
    private String desc;

    public Body(int id) {
        this.id = id;
    }

    public Body() {
    }

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
        return '\'' + desc + '\'';
    }
}
