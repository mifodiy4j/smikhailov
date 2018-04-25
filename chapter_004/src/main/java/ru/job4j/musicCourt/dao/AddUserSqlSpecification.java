package ru.job4j.musicCourt.dao;

import ru.job4j.musicCourt.dao.implement.AddressImpl;
import ru.job4j.musicCourt.domain.Address;
import ru.job4j.musicCourt.domain.MusicType;
import ru.job4j.musicCourt.domain.Role;
import ru.job4j.musicCourt.domain.User;

public class AddUserSqlSpecification implements SqlSpecification {

    private User user;
    private Address address;
    private Role role;
    private MusicType musicType;

    public AddUserSqlSpecification(User user, Address address, Role role, MusicType musicType) {
        this.user = user;
        this.address = address;
        this.role = role;
        this.musicType = musicType;
    }

    @Override
    public String toSqlQuery() {

        AddressImpl addressImpl = new AddressImpl();
        addressImpl.insert(address);

        user.setAdress(address);
        user.setRole(role);
        user.setMusicType(musicType);

        return String.format(
                "insert into users(id, name, age, address_id, role_id, musictype_id, password) values(%d, '%s', %d, %d, %d, %d, '%s')",
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getAdress().getId(),
                user.getRole().getId(),
                user.getMusicType().getId(),
                user.getPassword()
        );
    }
}
