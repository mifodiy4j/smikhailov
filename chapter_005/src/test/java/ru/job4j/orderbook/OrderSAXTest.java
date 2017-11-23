package ru.job4j.orderbook;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class OrderSAXTest {

    @Test
    public void whenOrderBookCreateForBookOne() {
        OrderSAX orderSAX = new OrderSAX("book-2", "orders.xml");
        try {
            orderSAX.order();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}