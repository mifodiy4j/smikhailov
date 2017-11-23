package ru.job4j.orderbook;

import java.util.*;

import static java.lang.String.format;
import static oracle.jrockit.jfr.events.Bits.intValue;

public class OrderBook {

    private static final String BUY = "BUY";

    private static final String SELL = "SELL";

    private static final String BID = "BID";

    private static final String ASK = "ASK";

    private final String nameBook;

    private final Map<Integer, Order> hashmap = new HashMap<>();
    Map<Float, Integer> hashmapOrder = new HashMap<>();

    public OrderBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void newOrder(Order order) {

        if (order instanceof DeleteOrder) {
            hashmap.remove(order.getOrderId());
        } else {
            hashmap.put(order.getOrderId(), order);
        }

    }

    public void convertTo() {

        for (Integer key: hashmap.keySet()) {

            Order order = hashmap.get(key);
            if (SELL.equals(order.getOperation())) {
                hashmapOrder.put(-(order.getPrice()), order.getVolume());
            } else if (BUY.equals(order.getOperation())) {
                hashmapOrder.put(order.getPrice(), order.getVolume());
            }
        }
    }

    @Override
    public String toString() {

        this.convertTo();

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Float, Integer> e : hashmapOrder.entrySet()) {
            String sf = String.format("%12s-%12s%n", e.getValue(), e.getKey());
            sb.append(sf);
        }
        return sb.toString();
    }

}