package ru.job4j.orderbook;

public abstract class Order implements Comparable<Order> {

    String book;

    String operation;

    float price;

    int volume;

    int orderId;

    /**
     * Constructs order.
     *
     * @param book      Order book.
     * @param operation Order operation.
     * @param price     Order price.
     * @param volume    Order volume.
     * @param orderId   Order id.
     */
    public Order(String book, String operation, float price, int volume, int orderId) {
        this.book = book;
        this.price = price;
        this.operation = operation;
        this.volume = volume;
        this.orderId = orderId;
    }

    public String getBook() {
        return book;
    }

    public int getOrderId() {
        return orderId;
    }

    public float getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public String getOperation() {
        return operation;
    }

    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else {
            if (obj instanceof Order) {
                result = compareTo((Order) obj) == 0;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return orderId;
    }

    @Override
    public int compareTo(Order obj) {
        return Integer.compare(orderId, obj.orderId);
    }
}