package ru.job4j;

import ru.job4j.models.*;

import java.util.*;

/**
 * Tracker.
 *
 * @author smikhailov
 * @version 1
 * @since 07.08.2017
 */
public class Tracker {

    ArrayList<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public ArrayList<Item> getAll() {
        ArrayList<Item> result;
        result = items;
        return result;
    }

    /**
     * Add.
     *
     * @param item
     * @return Item
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Update.
     *
     * @param item * @return void
     */
    public void update(Item item) {
        int i = 0;
        for (Item it : items) {
            if (it.getId().equals(item.getId())) {
                items.set(i, item);
            }
            i++;
        }
    }

    /**
     * Delete.
     *
     * @param item
     * @return void
     */
    public void delete(Item item) {
        items.remove(item);
    }

    /**
     * FindAll.
     *
     * @param
     * @return Item[]
     */
    public ArrayList<Item> findAll() {
        return items;
    }

    /**
     * FindByName.
     *
     * @param key
     * @return Item[]
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<Item>();
        items.forEach(item -> {
            if (item != null && item.getName().equals(key)) {
                result.add(item);
            }
        });
        return result;
    }

    /**
     * FindById.
     *
     * @param id
     * @return Item
     */
    public Item findById(String id) {
        Item result = null;
        try {
            items.forEach(item -> {
                if (item != null && item.getId().equals(id)) {
                    throw new BreakForFindByIdException(item);
                }
            });
        } catch (BreakForFindByIdException e) {
            result = e.getItem();
        }
        return result;
    }
}