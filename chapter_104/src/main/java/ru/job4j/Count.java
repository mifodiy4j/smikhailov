package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class Count {

    List<Double> diapason(int start, int end, Function<Double> func) {
        List<Double> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(func.operation(i));
        }
        return list;
    }
}

interface Function<T> {
    T operation(int argument);
}
