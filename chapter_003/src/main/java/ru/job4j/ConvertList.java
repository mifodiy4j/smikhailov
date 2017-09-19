package ru.job4j;

import java.util.*;

public class ConvertList {

    public List<Integer> toList (int[][] array) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    public int[][] toArray (List<Integer> list, int rows) {

        int column;
        if (list.size() % rows == 0) {
            column = list.size() / rows;
        } else {
            column = list.size() / rows + 1;
        }
        int[][] array = new int[rows][column];

        System.out.println(column);

        Iterator<Integer> iter = list.iterator();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                if (iter.hasNext()){
                    array[i][j] = iter.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }

        return array;
    }

    public List<Integer> convert (List<int[]> list) {
        List<Integer> result = new ArrayList<>();

        ListIterator<int[]> iter = list.listIterator();

        while (iter.hasNext()) {
            for (int  i = 0; i < iter.next().length; i++) {
                iter.previous();
                result.add(iter.next()[i]);
                iter.previous();
            }
        }
        return result;
    }

}
