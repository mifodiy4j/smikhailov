package ru.job4j.orderbook;

public class DeleteOrder extends Order {
    /**
     * Constructs order.
     *
     * @param book    Order book.
     * @param orderId Order id.
     */
    public DeleteOrder(String book, int orderId) {
        super(book, null, 0, 0, orderId);
    }

}