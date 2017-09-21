package ru.job4j.models;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User o) {
        if (age < o.age)
            return -1;
        else if (age > o.age)
            return 1;
        else
            return 0;
    }
}
