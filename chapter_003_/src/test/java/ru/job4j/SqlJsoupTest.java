package ru.job4j;

import org.junit.Test;

import java.io.IOException;

public class SqlJsoupTest {

    @Test
    public void test() throws IOException {
        SqlJsoup sqlJsoup = new SqlJsoup();
        sqlJsoup.parse();
    }

    @Test
    public void testAdd() {
        SqlJsoup sqlJsoup = new SqlJsoup();
        String s = sqlJsoup.convertDate("17 янв 18, 13:58");
        sqlJsoup.add("Ищем администратора Linux", s);
    }

    @Test
    public void testContaint() {
        SqlJsoup sqlJsoup = new SqlJsoup();
        System.out.println(sqlJsoup.stringContainsWorldJava("JavaScript"));
    }

    @Test
    public void testTableHasTheEntry() {
        SqlJsoup sqlJsoup = new SqlJsoup();
        System.out.println(sqlJsoup.noEntryInTable("Java разработчик. Удаленно"));
    }

}