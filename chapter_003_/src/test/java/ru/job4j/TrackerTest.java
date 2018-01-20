package ru.job4j;

import org.junit.Test;

/**
 * Created by SERG on 18.01.2018.
 */
public class TrackerTest {

    @Test
    public void testCreateURL () {
        Tracker tracker = new Tracker("tracker.xml");
        tracker.createURL();
    }

    @Test
    public void testCreateTable () {
        Tracker tracker = new Tracker("tracker.xml");
        tracker.createURL();
        tracker.createTable();
    }

    @Test
    public void testAddInTable () {
        Tracker tracker = new Tracker("tracker.xml");
        tracker.createURL();
        tracker.add();
    }

    @Test
    public void testFindAll () {
        Tracker tracker = new Tracker("tracker.xml");
        tracker.createURL();
        tracker.findAll();
    }

    @Test
    public void testFindById () {
        Tracker tracker = new Tracker("tracker.xml");
        tracker.createURL();
        tracker.findById(2);
    }

}