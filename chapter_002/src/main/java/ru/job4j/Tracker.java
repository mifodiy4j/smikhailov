package ru.job4j;

import ru.job4j.models.*;
import java.util.*;

/**
 * Tracker.
 *
 *@author smikhailov
 *@since 07.08.2017
 *@version 1
 */
public class Tracker {

	private Item[] items = new Item[100];
	private int position = 0;
	private static final Random RN = new Random();

	String generateId() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}

	public Item[] getAll() {
		Item[] result = new Item[this.position];
		for (int index = 0; index != this.position; index++) {
			result[index] = this.items[index];
		}
		return result;
	}

    /**
     * Add.
     * @param item
     * @return Item
     */
    public Item add(Item item) {
    	item.setId(this.generateId());
    	this.items[position++] = item;
    	return item;
    }

	/**
	 * Update.
	 * @param item
	 * * @return void
	 */
	public void update(Item item) {
        for (Item it : items) {
            if (it != null && it.getId().equals(item.getId())) {
                it = item;
                break;
            }
        }
    }

    /**
     * Delete.
     * @param item
     * @return void
     */
    public void delete(Item item) {
    	for (Item it : items) {
            if (it != null && it.getId().equals(item.getId())) {
                it = null;
                break;
            }
        }
    }

    /**
     * FindAll.
     * @param
     * @return Item[]
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        int i = 0;
        for (Item item : items) {
            if (item != null) {
                result[i++] = item;
            }
        }
        return result;
    }

    /**
     * FindByName.
     * @param key
     * @return Item[]
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int i = 0;
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                result[i++] = item;
            }
        }
        return result;
    }

    /**
     * FindById.
     * @param id
     * @return Item
     */
    public Item findById(String id) {
    	Item result = null;
    	for (Item item : items) {
    		if (item != null && item.getId().equals(id)) {
    			result = item;
    			break;
    		}
    	}
    	return result;
    }


}
