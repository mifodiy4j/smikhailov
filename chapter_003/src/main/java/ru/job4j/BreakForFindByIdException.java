package ru.job4j;

import ru.job4j.models.Item;

/**
 * BreakForFindByIdException.
 *
 * @author Sergey Mikhailov (mailto:mifodiy67@mail.ru)
 * @version 1
 * @since 09.07.2018
 */
public class BreakForFindByIdException extends RuntimeException {

    private Item item;

    public BreakForFindByIdException(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
