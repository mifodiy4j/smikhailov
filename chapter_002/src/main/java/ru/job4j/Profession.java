package ru.job4j;

import java.util.*;
/**
 * Profession.
 *
 *@author smikhailov
 *@since 03.08.2017
 *@version 1
 */
public class Profession {

	String name;
	int age;

	/**
	 * @param name
	 * @param age
	 */
	public Profession (String name, int age) {
		this.name = name;
		this .age = age;
	}

	/**
	 * GetName.
	 * @return
	 */
	public String getName() {
		return this.name;
	}

		
}