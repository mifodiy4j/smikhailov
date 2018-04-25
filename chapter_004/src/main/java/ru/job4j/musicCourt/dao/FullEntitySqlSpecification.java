package ru.job4j.musicCourt.dao;

import ru.job4j.musicCourt.domain.User;

public class FullEntitySqlSpecification implements SqlSpecification {

    private User user;

    public FullEntitySqlSpecification(User user) {
        this.user = user;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT address_id, role_id, musictype_id FROM users where id = %d", user.getId());
    }
}
