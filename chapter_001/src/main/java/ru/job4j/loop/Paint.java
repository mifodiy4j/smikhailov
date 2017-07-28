package ru.job4j.loop;

import java.util.*;
/**
    * Factorial.
    *
    *@author smikhailov
    *@since 28.07.2017
    *@version 1
    */
public class Paint {

	/**
        * Paint.
        *@param
        *@return
        *@throws
        */
	public String piramid(int h) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < (h * 2 - 1); j++) {
				if (j > (h - i - 2) && j < (h + i)) {
					sb.append("^");
				} else {
					sb.append(" ");
				}
			}
		}
		String cs = sb.toString();
		return cs;
	}
}