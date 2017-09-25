package ru.job4j;

import ru.job4j.models.User;

import java.util.*;

public class SortUser {

    public Set<User> sort (List<User> list) {
        Set<User> users = new TreeSet<>();

        ListIterator<User> iter = list.listIterator();

        while (iter.hasNext()) {
            users.add(iter.next());
            }
        return users;
    }

    public List<User> sortNameLength (List<User> list) {

        Collections.sort(list, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return o1.getName().length() - o2.getName().length();
                    }
                }
        );

        return list;
    }

    public List<User> sortByAllFields (List<User> list) {

        Collections.sort(list, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        final int rsl = o1.getName().compareTo(o2.getName());
                        return (rsl != 0) ? rsl : Integer.compare(o1.getAge(),o2.getAge());
                    }
                }
        );

        return list;
    }

}
