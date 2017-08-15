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
			this.showAll(tracker);
		} else if (EDIT.equals(answer)) {
			this.edit(tracker);
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
		
		String name = input.ask("Enter name: ");
		String description = input.ask("Enter description: ");

		tracker.add(new Item(name, description));
	}

	public void showAll(Tracker tracker) {
		Item[] result = new Item[100];
		result = tracker.getAll();
		//System.out.println("222");
		for (Item item : result) {
			//System.out.println("333");
			System.out.println(item.getName());
			//System.out.println("444");
		}
	}

	public void edit(Tracker tracker) {
		String nameOld = input.ask("Enter name: ");
		String descriptionOld = input.ask("Enter description: ");


		tracker.update(new Item(nameOld, descriptionOld));
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