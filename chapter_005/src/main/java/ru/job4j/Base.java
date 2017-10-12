package ru.job4j;

public abstract class Base {
    private String name;
    private String id;

    public Base(String name) {
        this.name = name;
    }

    public String getId()  {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
