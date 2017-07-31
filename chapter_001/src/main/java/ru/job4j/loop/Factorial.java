package ru.job4j.loop;

/**
    * Factorial.
    *
    *@author smikhailov
    *@since 27.07.2017
    *@version 1
    */
public class Factorial {

	/**
        * Calc.
        *@param
        *@return
        *@throws
        */
	public int calc(int n) {
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact = fact * i;
		}
		return fact;
	}
}