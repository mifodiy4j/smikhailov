package ru.job4j.array;

import java.util.*;
/**
    * Factorial.
    *
    *@author smikhailov
    *@since 1.08.2017
    *@version 1
    */
public class ArrayDuplicate {

	/**
        * Remove.
        *@param
        *@return
        *@throws
        */
	public String[] remove(String[] array) {
		String tmp;
        int n = 0;

		for (int i = 0; i < array.length;  i++) {
            //array[i] = tmp;
            for (int j = i + 1; j < array.length - n;  j++) {
                if (array[i].equals(array[j])) {
                    ++n;
                    tmp = array[j];
                    array[j] = array[array.length - n];
                    array[array.length - n] = tmp;
                }
            }
        }

		return Arrays.copyOf(array, array.length - n);
	}
}