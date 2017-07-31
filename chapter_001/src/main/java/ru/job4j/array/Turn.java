package ru.job4j.array;

import java.util.*;
/**
    * Factorial.
    *
    *@author smikhailov
    *@since 31.07.2017
    *@version 1
    */
public class Turn {

	/**
        * Back.
        *@param
        *@return
        *@throws
        */
	public int[] back(int[] array) {
		int bank;
		for (int i = 0; i < array.length / 2; i++) {
			bank = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = bank;
		}
		return array;
	}
}