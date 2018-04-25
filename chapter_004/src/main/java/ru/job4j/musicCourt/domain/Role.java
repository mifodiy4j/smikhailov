package ru.job4j.musicCourt.domain;

public class Role extends Model {

    private String role;

    public Role(int id) {
        super(id);
    }

    public Role() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
