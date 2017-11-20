package ru.job4j.orderbook;

public class AddOrder extends Order {
    /**
     * Constructs order.
     *
     * @param book      Order book.
     * @param operation Order operation.
     * @param price     Order price.
     * @param volume    Order volume.
     * @param orderId   Order id.
     */
    public AddOrder(String book, String operation, float price, int volume, int orderId) {
        super(book, operation, price, volume, orderId);
    }

}