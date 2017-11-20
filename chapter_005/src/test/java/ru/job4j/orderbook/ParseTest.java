package ru.job4j.orderbook;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParseTest {

    @Test
    public void whenParsingFileContainsTwelveAddOrdersShouldReturnCollectionContainsTwelveItem() {

        Parse parse = new Parse();
        TreeSet<Order> result = parse.par("orders-12.xml");

        TreeSet<Order> expected = new TreeSet<>();
        expected.add(new AddOrder("book-1", "SELL", 100.50f, 81, 1));
        expected.add(new AddOrder("book-1", "BUY", 99.50f, 86, 2));
        expected.add(new AddOrder("book-1", "BUY", 99.70f, 16, 3));
        expected.add(new AddOrder("book-1", "SELL", 100.00f, 80, 4));
        expected.add(new AddOrder("book-2", "SELL", 100.00f, 79, 5));
        expected.add(new AddOrder("book-2", "BUY", 99.40f, 78, 6));
        expected.add(new AddOrder("book-3", "SELL", 100.00f, 82, 7));
        expected.add(new AddOrder("book-2", "SELL", 100.20f, 42, 8));
        expected.add(new AddOrder("book-3", "SELL", 100.40f, 75, 9));
        expected.add(new AddOrder("book-1", "BUY", 99.80f, 64, 10));
        expected.add(new AddOrder("book-2", "SELL", 100.50f, 46, 11));
        expected.add(new AddOrder("book-3", "SELL", 100.00f, 48, 12));

        assertThat(result, is(expected));
    }

    @Test
    public void whenParsingFileContainsTwelveAddOrdersAndFourDeleteOrderShouldReturnCollectionContainsEightItem() {

        Parse parse = new Parse();
        TreeSet<Order> result = parse.par("orders-16.xml");

        TreeSet<Order> expected = new TreeSet<>();
        expected.add(new AddOrder("book-1", "SELL", 100.50f, 81, 1));
        expected.add(new AddOrder("book-1", "BUY", 99.50f, 86, 2));
        expected.add(new AddOrder("book-1", "SELL", 100.00f, 80, 4));
        expected.add(new AddOrder("book-2", "SELL", 100.00f, 79, 5));
        expected.add(new AddOrder("book-2", "BUY", 99.40f, 78, 6));
        expected.add(new AddOrder("book-2", "SELL", 100.20f, 42, 8));
        expected.add(new AddOrder("book-1", "BUY", 99.80f, 64, 10));
        expected.add(new AddOrder("book-3", "SELL", 100.00f, 48, 12));

        assertThat(result, is(expected));
    }

    @Test
    public void whenLoadWholeOrderBookThenItTakesLessThanSixSeconds() {
        long expected = 6000L;
        long start = System.currentTimeMillis();

        Parse parse = new Parse();
        TreeSet<Order> result = parse.par("orders.xml");

        long resultTime;
        resultTime = System.currentTimeMillis() - start;
        System.out.printf("%20s: Duration is %d millis", resultTime);

        assertTrue(resultTime < expected);
    }

}