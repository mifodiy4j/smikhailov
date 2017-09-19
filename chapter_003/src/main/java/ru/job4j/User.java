package ru.job4j;

import java.util.*;

public class User {

    private int id;
    private String name, city;

    public User (int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    static public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> users = new HashMap<Integer, User>();

        for (User user : list) {
            users.put(user.getId(), user);
        }
        return users;
    }

    /*public static void main(String[] args) {

        List<User> list = new ArrayList<>();

        list.add(new User(456, "Anton", "Moscow"));
        list.add(new User(789, "Boris", "Anapa"));
        list.add(new User(321, "Victor", "Tver"));

        HashMap<Integer, User> result = User.process(list);

        for (Integer id : result.keySet()) {
            System.out.println(id);
            System.out.println(result.get(id));
        }
    }*/
}