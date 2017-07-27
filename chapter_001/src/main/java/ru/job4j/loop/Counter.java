package ru.job4j.loop;

/**
    * Counter.
    *
    *@author smikhailov
    *@since 27.07.2017
    *@version 1
    */
public class Counter {

	/**
        * Add.
        *@param
        *@return
        *@throws
        */
	public int add(int start, int finish) {
		int sum = 0;
		for (int i = start; i <=finish; i++) {
			if (i % 2 == 0) {
				sum = sum + i;
			}
		}
		return sum;
	}
}