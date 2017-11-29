package ru.job4j.orderbook;

import java.util.*;

public class OrderBook {

    private static final String BUY = "BUY";

    private static final String SELL = "SELL";

    private static final String BID = "BID";

    private static final String ASK = "ASK";

    private final String nameBook;

    private final Map<Integer, Order> hashmap = new HashMap<>();
    private final TreeMap<Float, Integer> mapBuy = new TreeMap<>(
        new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {
                return o2.compareTo(o1);
            }
        }
    );
    private final TreeMap<Float, Integer> mapSell = new TreeMap<>();

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

                if (mapSell.containsKey(order.getPrice())) {
                    int newVolume = mapSell.get(order.getPrice()) + order.getVolume();
                    mapSell.put(order.getPrice(), newVolume);
                } else {
                    mapSell.put(order.getPrice(), order.getVolume());
                }
            } else if (BUY.equals(order.getOperation())) {

                if (mapBuy.containsKey(order.getPrice())) {
                    int newVolume = mapBuy.get(order.getPrice()) + order.getVolume();
                    mapBuy.put(order.getPrice(), newVolume);
                } else {
                    mapBuy.put(order.getPrice(), order.getVolume());
                }
            }
        }
    }

    public void print() {

        this.convertTo();

        System.out.printf("Order book: ${ %s }%n%n", nameBook);
        System.out.printf("%10s%19s%n", BID, ASK);
        System.out.printf("%8s@%-8s", "Volume", "Price");
        System.out.printf("- %8s@%-8s%n", "Volume", "Price");

        if (mapBuy.firstKey() >= mapSell.firstKey()) {

            for (Map.Entry<Float, Integer> eBuy : mapBuy.entrySet()) {
                for (Map.Entry<Float, Integer> eSell : mapSell.entrySet()) {
                    if (eBuy.getKey().equals(eSell.getKey())) {
                        int val = eBuy.getValue() - eSell.getValue();
                        if (val > 0) {
                            eBuy.setValue(val);
                        } else if (val < 0) {
                            eSell.setValue(Math.abs(val));
                        } else {
                            mapBuy.remove(eBuy.getKey());
                            mapSell.remove(eSell.getKey());
                        }
                    }
                }
            }
        }

        while(!mapBuy.isEmpty() || !mapSell.isEmpty()) {
            if (mapBuy.isEmpty()) {
                System.out.printf("   -----------   ");
            } else {
                System.out.printf("%8s@%-8s", mapBuy.get(mapBuy.firstKey()), mapBuy.firstKey());
                mapBuy.remove(mapBuy.firstKey());
            }

            if (mapSell.isEmpty()) {
                System.out.printf("   -----------   ");
            } else {
                System.out.printf("- %8s@%-8s%n", mapSell.get(mapSell.firstKey()), mapSell.firstKey());
                mapSell.remove(mapSell.firstKey());
            }
        }
    }
}