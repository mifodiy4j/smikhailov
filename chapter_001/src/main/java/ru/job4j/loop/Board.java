package ru.job4j.loop;

import java.util.*;
/**
    * Factorial.
    *
    *@author smikhailov
    *@since 28.07.2017
    *@version 1
    */
public class Board {

	/**
        * Paint.
        *@param
        *@return
        *@throws
        */
	public String paint(int width, int height) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if ((i + j) % 2 == 0) {
					sb.append("X");
				} else {
					sb.append(" ");
				}
			}
		}
		String cs = sb.toString();
		return cs;
	}
}