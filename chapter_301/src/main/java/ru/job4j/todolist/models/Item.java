package ru.job4j.todolist.models;

import java.sql.Timestamp;
import java.util.Date;

public class Item {

    private int id;
    private String description;
    private Timestamp created;
    //private Date created;
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

   /* public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }*/

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
