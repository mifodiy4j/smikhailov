package ru.job4j;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  Sergey Mikhailov
 * @since   1.0
 */
public class SqlJsoup implements Job{

    private String nameTable = "job";

    String nameColumn = "comment, date_publish";
    String nameValue;

    String url = "jdbc:postgresql://localhost:5432/sql_jsoup";
    String username = "postgres";
    String password = "root";

    /**
     * Метод добавляет новую запись в таблиу
     */
    public void add(String name, String date) {

        nameValue = "'" + name + "','" + date + "'";

        Connection conn = null;

        String strAddItem = "insert into " +
                nameTable +
                "(" +
                nameColumn +
                ") values(" +
                nameValue +
                ");";

        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            st.execute(strAddItem);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод проверяет наличие в строке слова "Java"
     * при этом "JavaScript" и "Java Script" не подходит для поиска
     * @param input проверяемая строка
     * @return <code>true</code> если строка <code>input</code> содержит слово "Java"
     * <code>false</code> в противоположенном случае
     */
    public boolean stringContainsWorldJava(String input) {

        boolean result = false;

        Pattern pattern1 = Pattern.compile("Java(\\W{1})+\\w*");
        Matcher matcher1 = pattern1.matcher(input);
        while (matcher1.find()) {
            String str1 = matcher1.group();
            result = true;

            Pattern pattern2 = Pattern.compile(".+\\s+Script");
            Matcher matcher2 = pattern2.matcher(str1);
            while (matcher2.find()) {
                String str2 = matcher1.group();
                result = false;
            }
        }

        return result;
    }

    /**
     * Метод конвертирует строковое представление даты
     * (24 янв 18, 14:02; сегодня, 09:05; вчера, 20:32)
     * в строковое представление даты для добавления в
     * базу данных (2018-01-24 14:02:00)
     *
     * @param str строковое представление полученное с сайта
     * @return строковое представление даты в формате (yyyy-MM-dd HH:mm:ss)
     */
    public String convertDate(String str) {

        String months[] = {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен",
                "окт", "ноя", "дек"};
        String year = "";
        String month = "";
        String day = "";
        String result = "";

        String[] array_1 = str.split(",");
        String time = array_1[1];
        String[] array_2;
        if ("сегодня".equals(array_1[0])) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd ");
            DateTime dt_today = new DateTime();
            result = dtf.print(dt_today);
        } else if ("вчера".equals(array_1[0])) {
            DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd ");
            DateTime dt_yesterday = new DateTime().minusDays(1);
            result = dtf.print(dt_yesterday);
        } else {
            array_2 = array_1[0].split(" ");
            year = "20" + array_2[2];
            month = array_2[1];
            day = array_2[0];
            result = year + "-" + Integer.toString(Arrays.asList(months).indexOf(month) + 1) + "-" + day + " ";
        }
        result += time;

        return result;
    }

    /**
     * Метод проверяет что год в строке с датой и временем <code>input</code> формата (yyyy-MM-dd HH:mm:ss)
     * совпадает с текущим
     * @param input строка с датой и временем формата (yyyy-MM-dd HH:mm:ss)
     * @return <code>true</code> если год в строке <code>input</code> совпадает с текущим годом
     * <code>false</code> в противоположенном случае
     */
    public boolean dateIsCurrentYear(String input) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy");
        DateTime dt_today = new DateTime();
        String currentYear = dtf.print(dt_today);

        return currentYear.equals(input.split("-")[0]) ? true : false;
    }

    /**
     * Метод проверяет на наличие записи в базе данных
     * @param entry строка проверяемая на наличие в базе данных
     * @return <code>true</code> если строки <code>entry</code> нет в базе данных
     * <code>false</code> если строка есть в базе данных
     */
    public boolean noEntryInTable(String entry) {

        Connection conn = null;
        int count = 0;

        String strFindByComment = "SELECT COUNT(id) FROM " +
                nameTable +
                " WHERE comment = ?";

        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(strFindByComment);
            st.setString(1, entry);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (count == 0) ? true : false;
    }

    /**
     * Метод парсит парсит страницы http://sql.ru/forum/job/1..
     * и добавляет все записи со словом "Java" текущего года
     * в базу данных, без повторов
     * @throws IOException
     */
    public void parse() throws IOException {
        boolean flagCurrentYear = true;
        int numberPage = 1;

        do {
            Document doc = Jsoup.connect("http://sql.ru/forum/job/" + numberPage++).get();

            Elements trElements = doc.getElementsByTag("tr");

            for (Element trElement : trElements) {

                Elements tdElements = trElement.getElementsByTag("td");

                if (tdElements.size() == 6) {
                    if ("icon_cell".equals(tdElements.get(0).attr("class"))) {
                        String comment = tdElements.get(1).child(0).text();
                        String date = tdElements.get(5).text();

                        String convertDate = convertDate(date);
                        flagCurrentYear = dateIsCurrentYear(convertDate);

                        if (stringContainsWorldJava(comment) && flagCurrentYear && noEntryInTable(comment)) {
                            add(comment, convertDate);
                        }
                    }
                }
            }
        } while (flagCurrentYear);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
