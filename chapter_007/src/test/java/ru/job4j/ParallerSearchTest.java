package ru.job4j;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParallerSearchTest {

    @Test
    public void test() throws IOException, InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("txt");
        list.add("doc");

        ParallerSearch parallerSearch = new ParallerSearch("d:\\test", "qwerty", list);

        System.out.println(parallerSearch.result());
    }

}