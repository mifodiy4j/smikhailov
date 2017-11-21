package ru.job4j.orderbook;

import java.util.*;

import static java.lang.String.format;

public class OrderBook {
    /**
     * Order BUY operation (for BID actions).
     */
    private static final String BUY = "BUY";
    /**
     * Order SELL operation (for ASK actions).
     */
    private static final String SELL = "SELL";
    /**
     * Order BID action.
     */
    private static final String BID = "BID";
    /**
     * Order ASK action.
     */
    private static final String ASK = "ASK";

    /**
     * Order book name.
     */
    private final String name;
    /**
     * Internal set of matched orders.
     */
    private final TreeSet<MatchedOrder> matched;

    /**
     * Constructs order book with name.
     *
     * @param name Order book name.
     */
    public OrderBook(String name) {
        this.name = name;
        this.matched = new TreeSet<>();
    }

    /**
     * Adds new order into order book.
     *
     * @param order New order.
     */
    public void newOrder(Order order) {
        MatchedOrder m = new MatchedOrder(order);
        if (this.matched.contains(m)) {
            m = this.matched.ceiling(m);
            m.volume += BUY.equals(order.getOperation()) ? -order.getVolume() : order.getVolume();
        } else {
            this.matched.add(m);
        }
        if (m.volume == 0) {
            this.matched.remove(m);
        }
    }

    /**
     * Generated the list of sell matched orders.
     *
     * @return Sell orders list.
     */
    private List<MatchedOrder> asAskList() {
        List<MatchedOrder> result = new LinkedList<>();
        Iterator<MatchedOrder> it = this.matched.iterator();
        while (it.hasNext()) {
            MatchedOrder order = it.next();
            if (order.volume > 0) {
                result.add(order);
            }
        }
        return result;
    }

    /**
     * Generated the list of buy matched orders.
     *
     * @return buy orders list.
     */
    private List<MatchedOrder> asBidList() {
        List<MatchedOrder> result = new LinkedList<>();
        Iterator<MatchedOrder> it = this.matched.descendingIterator();
        while (it.hasNext()) {
            MatchedOrder order = it.next();
            if (order.volume < 0) {
                result.add(order);
            }
        }
        return result;
    }

    /**
     * toString override.
     *
     * @return String representing the order book object.
     */
    @Override
    public String toString() {
        Iterator<MatchedOrder> bid = this.asBidList().iterator();
        Iterator<MatchedOrder> ask = this.asAskList().iterator();

        StringBuilder sb = new StringBuilder();
        sb.append(format("%n%22s%s%n", "Order book: ", this.name));
        sb.append(format("%n%12s%10s%12s%n", BID, "-", ASK));
        sb.append(format("%n%10s@%-10s%s%10s@%-10s%n", "Volume", "Price", "-", "Volume", "Price"));

        while (bid.hasNext() && ask.hasNext()) {
            sb.append(format("%21s-%21s%n", bid.next(), ask.next()));
        }
        while (bid.hasNext()) {
            sb.append(format("%21s-%16s%n", bid.next(), "-----------"));
        }
        while (ask.hasNext()) {
            sb.append(format("%16s%6s%21s%n", "-----------", "-", ask.next()));
        }

        return sb.toString();
    }

    /**
     * Class MatchedOrder.
     */
    private class MatchedOrder implements Comparable<MatchedOrder> {
        /**
         * Order price.
         */
        private final float price;
        /**
         * Order volume.
         */
        private int volume;

        /**
         * Constructs matched order object.
         *
         * @param order Order object.
         */
        MatchedOrder(Order order) {
            this.price = order.getPrice();
            this.volume = BUY.equals(order.getOperation()) ? -order.getVolume() : order.getVolume();
        }

        /**
         * Compares this object with the specified object for order.
         */
        @Override
        public int compareTo(MatchedOrder other) {
            return Float.compare(price, other.price);
        }

        /**
         * Equality method.
         *
         * @param obj Other MatchedOrder object.
         * @return True if prices are equal.
         */
        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (this == obj) {
                result = true;
            } else {
                if (obj != null && getClass() == obj.getClass()) {
                    MatchedOrder other = (MatchedOrder) obj;
                    result = Float.compare(other.price, price) == 0;
                }
            }
            return result;
        }

        /**
         * Calculates hash code on price only.
         *
         * @return Hash code.
         */
        @Override
        public int hashCode() {
            return (price != +0.0f ? Float.floatToIntBits(price) : 0);
        }

        /**
         * toString implementation.
         *
         * @return String representing matched order.
         */
        @Override
        public String toString() {
            return format(Locale.US, "%-10d@%10.2f", Math.abs(volume), price);
        }
    }
}