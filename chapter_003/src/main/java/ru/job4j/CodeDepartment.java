package ru.job4j;

        import java.util.*;
        import java.util.regex.Pattern;

public class CodeDepartment {

    public static void main(String[] args) {
        CodeDepartment codeDepartment = new CodeDepartment();

        TreeSet<String> departments = new TreeSet<>(new CompFromFirstToLast());
        //TreeSet<String> departments = new TreeSet<>(new CompFromLastToFirst());
        departments.add("K1\\SK1");
        departments.add("K1\\SK2");
        departments.add("K1\\SK1\\SSK1");
        departments.add("K1\\SK1\\SSK2");
        departments.add("K2");
        departments.add("K2\\SK1\\SSK1");
        departments.add("K2\\SK1\\SSK2");

        System.out.println(departments);

        codeDepartment.zapoln(departments, departments.last());
        codeDepartment.zapoln(departments, departments.first());

        System.out.println(departments);

    }

    public void zapoln(TreeSet<String> coll, String str) {
        String separator = "\\";
        String[] subStr = str.split(Pattern.quote(separator));

        for (int i = 0; i < subStr.length; i++) {
            String strName = subStr[0];
            for(int j = 1; j <= i; j++) {
                strName = strName + "\\" + subStr[j];
            }
            coll.add(strName);
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