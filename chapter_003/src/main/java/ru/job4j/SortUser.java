package ru.job4j;

import ru.job4j.models.User;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    public Set<User> sort (List<User> list) {
        Set<User> users = new TreeSet<>();

        ListIterator<User> iter = list.listIterator();

        while (iter.hasNext()) {
            users.add(iter.next());
            }
        return users;

    }
}
