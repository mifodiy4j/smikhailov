package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.sql.*;
import java.util.*;

/**
 * Tracker.
 *
 *@author smikhailov
 *@since 18.01.2018
 *@version 1
 *
 * Создает на базе переданного XML файла таблиу в базе данных.
 * Архитектура таблиы задается в XML файле.
 * Добавленик нового элемента в таблиу осуществляется с помощью
 * записи в XML файл.
 *
 */
public class Tracker {

    private static final Logger Log = LoggerFactory.getLogger(SQL_Storage.class);

    private String fileName;
    private String fileNameForAdd;
    private String nameDB = "";
    private String port;
    private String url;
    private String username = "";
    private String password = "";
    private String nameTable = "";

    String nameColumn;
    String nameValue;

    private Map<String, String> column = new TreeMap<>();

    /**
     * Конструктор
     * @param fileName - имя XML файла, в котором указаны
     *                 настройки базы данных и свойства новой табдиы.
     */
    public Tracker(String fileName, String fileNameForAdd) {
        this.fileName = fileName;
        this.fileNameForAdd = fileNameForAdd;
    }

    /**
     * Метод формирует URL запрос на базе данных, полученных
     * после разбора XML файла
     */
    public void createURL () {
        parsingXMLForCreate();

        url = "jdbc:postgresql://localhost";
        if (port != null) {
            url += ":" + port;
        }

        url += "/" + nameDB;

        System.out.printf("URL = %s %n", url);
        System.out.printf("USERNAME = %s %n", username);
        System.out.printf("PASSWORD = %s %n", password);
    }

    /**
     * Метод разбирает XML файл на данные необходимые для подключения
     * к базе данных и создания новой таблиы с задданными свойствами
     */
    public void parsingXMLForCreate() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                private Stack<String> names = new Stack<String>();

                private Stack<String> elementStack = new Stack<String>();

                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes) throws SAXException {

                    this.elementStack.push(qName);
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                    this.elementStack.pop();
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    String value = new String(ch, start, length).trim();

                    if ("nameDB".equals(currentElement()) &&
                            "database".equals(currentElementParent())) {
                        nameDB = value;
                    }

                    if ("port".equals(currentElement()) &&
                            "database".equals(currentElementParent())) {
                        port = value;
                    }

                    if ("login".equals(currentElement()) &&
                            "database".equals(currentElementParent())) {
                        username = value;
                    }

                    if ("password".equals(currentElement()) &&
                            "database".equals(currentElementParent())) {
                        password = value;
                    }

                    if ("createTable".equals(currentElement()) &&
                            "database".equals(currentElementParent())) {
                        nameTable = value;
                    }

                    if ("name".equals(currentElement()) &&
                            "column".equals(currentElementParent())) {
                        names.push(value);
                    }

                    if ("type".equals(currentElement()) &&
                            "column".equals(currentElementParent())) {
                        column.put(names.pop(), value);
                    }
                }

                private String currentElement() {
                    return this.elementStack.peek();
                }

                private String currentElementParent() {
                    if(this.elementStack.size() < 2) return null;
                    return this.elementStack.get(this.elementStack.size()-2);
                }
            };

            saxParser.parse(new File(fileName), handler);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает таблиу в базе данных.
     */
    public void createTable() {
        Connection conn = null;

        String strCreateTable = "create table if NOT EXISTS " + nameTable + "(";
        for(Iterator<String> it = column.keySet().iterator(); it.hasNext();) {
            String s = it.next();
            strCreateTable += s + " " + column.get(s) + ", ";
        }

        strCreateTable = strCreateTable.substring(0, strCreateTable.length() - 2);

        strCreateTable += ");";

        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            st.execute(strCreateTable);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    Log.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Метод разбирает XML файл на данные необходимые для
     * вставки новой записи в созданную таблиу
     */
    public void parsingXMLForAdd() {

        nameColumn ="";
        nameValue ="";

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                private Stack<String> elementStack = new Stack<String>();

                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes) throws SAXException {

                    this.elementStack.push(qName);
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                    this.elementStack.pop();
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    String value = new String(ch, start, length).trim();

                    if ("name".equals(currentElement()) &&
                            "insert".equals(currentElementParent()) &&
                            value != null) {
                        nameColumn += currentElement() + ", ";
                        nameValue += "'" + value + "', ";
                    }

                    if ("description".equals(currentElement()) &&
                            "insert".equals(currentElementParent()) &&
                            value != null) {
                        nameColumn += currentElement() + ", ";
                        nameValue += "'" + value + "', ";
                    }

                    if ("create_date".equals(currentElement()) &&
                            "insert".equals(currentElementParent()) &&
                            value != null) {
                        nameColumn += currentElement() + ", ";
                        nameValue += "'" + value + "', ";
                    }

                    if ("comments".equals(currentElement()) &&
                            "insert".equals(currentElementParent()) &&
                            value != null) {
                        nameColumn += currentElement();
                        nameValue += "'" + value + "'";
                    }
                }

                private String currentElement() {
                    return this.elementStack.peek();
                }

                private String currentElementParent() {
                    if(this.elementStack.size() < 2) return null;
                    return this.elementStack.get(this.elementStack.size()-2);
                }
            };

            saxParser.parse(new File(fileNameForAdd), handler);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод добавляет новую запись в таблиу
     */
    public void add() {
        parsingXMLForAdd();

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
     * Метод выводит в консоль все записи созданной таблиы
     */
    public void findAll() {
        Connection conn = null;

        String strAddItem = "select * from " +
                nameTable +
                ";";

        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(strAddItem);

            while (rs.next()) {
                System.out.println(String.format("%d %s %s %s %s", rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("create_date"), rs.getString("comments")));
            }
            rs.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод выводит запись из таблиы в консоль с заданным id
     * @param id - первичный ключ записи в таблие
     */
    public void findById(int id) {
        Connection conn = null;

        String strFindById = "SELECT * FROM " +
                nameTable +
                " WHERE id = ?";

        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement(strFindById);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                System.out.println(String.format("%d %s %s %s %s", rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("create_date"), rs.getString("comments")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
