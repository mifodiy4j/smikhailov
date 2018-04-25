package ru.job4j.musicCourt.domain;

import java.io.Serializable;

public abstract class Model implements Serializable{

    private int id;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        return id == model.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
