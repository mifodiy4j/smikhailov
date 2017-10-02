package ru.job4j;

import java.util.*;
import java.util.regex.Pattern;

public class CodeDepartment {
    TreeSet<String> departments = new TreeSet<>(new CompFromFirstToLast());
    TreeSet<String> departmentsReverse = new TreeSet<>(new CompFromLastToFirst());

    public void add(String department) {
        departments.add(department);
        departmentsReverse.add(department);

        String separator = "\\";
        String[] subStr = department.split(Pattern.quote(separator));
        for (int i = 0; i < subStr.length; i++) {
            String strName = subStr[0];
            for(int j = 1; j <= i; j++) {
                strName = strName + "\\" + subStr[j];
            }
            departments.add(strName);
            departmentsReverse.add(strName);
        }
    }

}

class CompFromFirstToLast implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}

class CompFromLastToFirst implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        return s2.compareTo(s1);
    }
}