package ru.job4j.array;

import java.util.*;
/**
    * Factorial.
    *
    *@author smikhailov
    *@since 31.07.2017
    *@version 1
    */
public class RotateArray {

	/**
        * Rotate.
        *@param
        *@return
        *@throws
        */
	public int[][] rotate(int[][] array) {
		int bank;
        int n = array.length;

		for (int i = 0; i < (n / 2); i++) {
			for (int j = i; j < (n - i - 1); j++) {
    			
                bank = array[i][j];
                array[i][j] = array[n - j - 1][i];
                array[n - j - 1][i] = array[n - i - 1][n - j - 1];
                array[n - i - 1][n - j - 1] = array[j][n - i - 1];
                array[j][n - i - 1] = bank;
            }
		}
		return array;
	}
}