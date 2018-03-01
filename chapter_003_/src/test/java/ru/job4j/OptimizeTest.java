package ru.job4j;

import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class OptimizeTest {

    @Test
    public void testCreateTable() {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "postgres", 10);
        optimize.createTable();
    }

    @Test
    public void testEnterRecordsInTable() {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "root", 10);
        optimize.enterRecordsInTable();
    }

    @Test
    public void testDeleteRecordsInTable() {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "root", 10);
        optimize.deleteRecordsInTable();
    }

    @Test
    public void testCreateXML() throws ParserConfigurationException, TransformerException {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "root", 10);
        optimize.createXML();
    }

    @Test
    public void testoutRecordsFromTable() throws ParserConfigurationException, TransformerException {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "root", 10);
        optimize.outRecordsFromTable();
    }

    @Test
    public void testEnterRecordsInXML() throws ParserConfigurationException, TransformerException {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "root", 10);
        optimize.enterRecordsInXML();
    }

    @Test
    public void testModernXML() throws ParserConfigurationException, TransformerException {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "root", 10);
        optimize.modernXML();
    }
    @Test
    public void testParsingXML() throws ParserConfigurationException, TransformerException {
        Optimize optimize = new Optimize("jdbc:postgresql://localhost:5432/SQLite", "postgres", "root", 10);
        optimize.parsingXML();
    }

}