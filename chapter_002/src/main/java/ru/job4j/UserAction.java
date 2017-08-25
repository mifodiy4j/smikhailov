package ru.job4j;

/**
 * UserAction.
 *
 *@author smikhailov
 *@since 25.08.2017
 *@version 1
 */
public interface UserAction {
	
	int key();

	void execute(Input input, Tracker tracker);

	String info();
}