package ru.job4j.example.models;

public class Comment {
    private int id;
    private String desc;
    private int spent;
    private Item item;

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

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", spent=" + spent +
                '}';
    }
}
