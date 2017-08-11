package ru.job4j;

import ru.job4j.models.*;

public class StartUI {
	private Input input;
	
	private static boolean notExit = true;

	private static final String ADD = "0";
	private static final String SHOWALL = "1";
	private static final String EDIT = "2";
	private static final String DEL = "3";
	private static final String FINDID = "4";
	private static final String FINDNAME = "5";
	private static final String EXIT = "6";

	public StartUI(Input input) {
		this.input = input;
	}

	public void init() {
		System.out.println("0. Add new Item");
		System.out.println("1. Show all items");
		System.out.println("2. Edit item");
		System.out.println("3. Delete item");
		System.out.println("4. Find item by Id");
		System.out.println("5. Find items by name");
		System.out.println("6. Exit Program");

		String answer = input.ask("Select: ");

		Tracker tracker = new Tracker();
		
		if (ADD.equals(answer)) {
			this.createItem(tracker);
		} else if (SHOWALL.equals(answer)) {
			this.getAll(tracker);
		} else if (EDIT.equals(answer)) {
			this.update(tracker);
		} else if (DEL.equals(answer)) {
			this.delete(tracker);
		} else if (FINDID.equals(answer)) {
			this.findById(tracker);
		} else if (FINDNAME.equals(answer)) {
			this.findByName(tracker);
		} else if (EXIT.equals(answer)) {
			notExit	= false;	
		} else {
			System.out.println("Please, try again");
		}

	}

	public void createItem(Tracker tracker) {
		tracker.add(new Item("first task", "first desc", 123L));
	}

	public void getAll(Tracker tracker) {
		tracker.getAll();
	}

	public void update(Tracker tracker) {
		tracker.update(new Item("first task", "second desc", 123L));
	}

	public void delete(Tracker tracker) {
		tracker.delete(new Item("first task", "second desc", 123L));
	}

	public void findById(Tracker tracker) {
		tracker.findById("123456");
	}

	public void findByName(Tracker tracker) {
		tracker.findByName("first task");
	}

	public static void main(String[] args) {
		Input input = new ConsoleInput();
		
		while (notExit) {
			new StartUI(input).init();
		}
	}
}