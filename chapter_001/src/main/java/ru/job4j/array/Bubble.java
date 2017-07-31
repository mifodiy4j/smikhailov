package ru.job4j.array;

import java.util.*;
/**
    * Factorial.
    *
    *@author smikhailov
    *@since 31.07.2017
    *@version 1
    */
public class Bubble {

	/**
        * Sort.
        *@param
        *@return
        *@throws
        */
	public int[] sort(int[] array) {
		int bank;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
    			if (array[j] > array[j + 1]) {
                    bank = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = bank;
                }
            }
		}
		return array;
	}
}