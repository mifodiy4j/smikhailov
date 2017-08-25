package ru.job4j;

import ru.job4j.models.*;

public class StartUI {
	private Input input;
	//Tracker tracker;
	
	private static boolean notExit = true;

	private static final String ADD = "0";
	private static final String SHOWALL = "1";
	private static final String EDIT = "2";
	private static final String DEL = "3";
	private static final String FINDID = "4";
	private static final String FINDNAME = "5";
	private static final String EXIT = "6";

	/*public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}*/

	public StartUI(Input input) {
		this.input = input;
	}

	public void init() {

		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();

		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Select: "));
			menu.select(key);
		} while(!"y".equals(this.input.ask("Exit? (y) : ")));

		/*System.out.println("0. Add new Item");
		System.out.println("1. Show all items");
		System.out.println("2. Edit item");
		System.out.println("3. Delete item");
		System.out.println("4. Find item by Id");
		System.out.println("5. Find items by name");
		System.out.println("6. Exit Program");

		String answer = input.ask("Select: ");

		while (notExit) {
		
			if ((Integer.parseInt(answer) < 0) && (Integer.parseInt(answer) > 6)) {
				System.out.println("Please, try again!");
			}else if (ADD.equals(answer)) {
				this.createItem(tracker);
				answer = input.ask("Select: ");
			} else if (SHOWALL.equals(answer)) {
				this.showAll(tracker);
				answer = input.ask("Select: ");
			} else if (EDIT.equals(answer)) {
				this.edit(tracker);
				answer = input.ask("Select: ");
			} else if (DEL.equals(answer)) {
				this.delete(tracker);
				answer = input.ask("Select: ");
			} else if (FINDID.equals(answer)) {
				this.findById(tracker);
				answer = input.ask("Select: ");
			} else if (FINDNAME.equals(answer)) {
				this.findByName(tracker);
				answer = input.ask("Select: ");
			} else if (EXIT.equals(answer)) {
				notExit	= false;
			}
		}*/

	}

	public void createItem(Tracker tracker) {
		
		String name = input.ask("Enter name: ");
		String description = input.ask("Enter description: ");
		long create = System.currentTimeMillis();

		tracker.add(new Item(name, description, create));
	}

	public void showAll(Tracker tracker) {
		System.out.println("--------------------");
		for (Item item : tracker.getAll()) {
			System.out.println("Name :  " + item.getName());
			System.out.println("Desc :  " + item.getDescription());
			System.out.println("Create: " + item.getCreate());
			System.out.println("ID :    " + item.getId());
			System.out.println("--------------------");
		}
	}

	public void edit(Tracker tracker) {
		String id = input.ask("Enter ID: ");
		String name = input.ask("Enter name: ");
		String description = input.ask("Enter description: ");
		long create = System.currentTimeMillis();

		Item item = new Item(name, description, create);
		item.setId(id);

		tracker.update(item);
	}

	public void delete(Tracker tracker) {
		String id = input.ask("Enter ID: ");
		
		if (tracker.findById(id) == null) {
			System.out.println(id + " not found");
		} else {
			tracker.delete(tracker.findById(id));
			System.out.println("Remov " + id + " was successful");
		}
	}

	public void findById(Tracker tracker) {
		String id = input.ask("Enter ID: ");

		System.out.println("Name :  " + tracker.findById(id).getName());
		System.out.println("Desc :  " + tracker.findById(id).getDescription());
		System.out.println("Create: " + tracker.findById(id).getCreate());
	}

	public void findByName(Tracker tracker) {
		String name = input.ask("Enter name: ");

		if (tracker.findByName(name).length == 0) {
			System.out.println("Not found");
		} else {
			System.out.println("--------------------");
			for (Item item : tracker.findByName(name)) {
				System.out.println("Name :  " + item.getName());
				System.out.println("Desc :  " + item.getDescription());
				System.out.println("Create: " + item.getCreate());
				System.out.println("ID :    " + item.getId());
				System.out.println("--------------------");
			}
		}
	}

	public static void main(String[] args) {
		Input input = new ConsoleInput();
		/*Tracker tracker = new Tracker();
		new StartUI(input, tracker).init();*/
		new StartUI(input).init();
	}
}