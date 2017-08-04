package ru.job4j;

import java.util.*;
/**
 * Doctor.
 *
 *@author smikhailov
 *@since 03.08.2017
 *@version 1
 */
public class Doctor extends Profession {

	public Doctor(String name, int age) {
		super(name, age);
	}

	/**
	 * @param pacient
	 * @return
	 */
	public String heal(Profession pacient) {
		return "Доктор " + this.getName() + " лечит " + pacient.getName();
	}
		
}