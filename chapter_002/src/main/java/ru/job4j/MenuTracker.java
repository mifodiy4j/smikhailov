package ru.job4j;

import ru.job4j.models.*;

/**
 * MenuTracker.
 *
 *@author smikhailov
 *@since 25.08.2017
 *@version 1
 */

class EditItem implements UserAction {
	public int key() {
		return 2;
	}

	public void execute(Input input, Tracker tracker) {
		String id = input.ask("Please, enter the taks's id: ");
		String name = input.ask("Please, enter the taks's name: ");
		String desc = input.ask("Please, enter the taks's desc: ");
		long create = System.currentTimeMillis();
		Item item = new Item(name, desc, create);
		item.setId(id);
		tracker.update(item);
	}

	public String info() {
		return String.format("%s, %s", this.key(), "Edit item");
	}
}

class FindItemByName implements UserAction {
	private int i = 0;
	public int key() {
		return 5;
	}

	public void execute(Input input, Tracker tracker) {
		String key = input.ask("Please, enter the taks's name: ");

		for (Item item : tracker.findByName(key)) {
			System.out.println(++i + "--------------");
			System.out.println("Name        : " + item.getName());
			System.out.println("Description : " + item.getDescription());
		}
		
	}

	public String info() {
		return String.format("%s, %s", this.key(), "Find items by name");
	}
}

class ExitProgram implements UserAction {
	public int key() {
		return 6;
	}

	public void execute(Input input, Tracker tracker) {
		
	}

	public String info() {
		return String.format("%s, %s", this.key(), "Exit Program");
	}
}

public class MenuTracker {

	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[7]; 

	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	public void fillActions() {
		this.actions[0] = this.new AddItem();
		this.actions[1] = new MenuTracker.ShowItems();
		this.actions[2] = new EditItem();
		this.actions[3] = this.new DelItem();
		this.actions[4] = new MenuTracker.FindItemById();
		this.actions[5] = new FindItemByName();
		this.actions[6] = new ExitProgram();
	}

	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}

	public void show() {
		for (UserAction action : this.actions) {
			if (action != null) {
				System.out.println(action.info());
			}
		}
	}

	private class AddItem implements UserAction {
		public int key() {
			return 0;
		}

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the taks's name: ");
			String desc = input.ask("Please, enter the taks's desc: ");
			long create = System.currentTimeMillis();
			tracker.add(new Item(name, desc, create));
		}

		public String info() {
			return String.format("%s, %s", this.key(), "Add new item");
		}
	}

	private static class ShowItems implements UserAction {
		public int key() {
			return 1;
		}

		public void execute(Input input, Tracker tracker) {
			for (Item item : tracker.getAll()) {
				System.out.println(
					String.format("%s. %s", item.getId(), item.getName())
				);
			}
		}

		public String info() {
			return String.format("%s, %s", this.key(), "Show all items");
		}
	}

	private class DelItem implements UserAction {
		public int key() {
			return 3;
		}

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please, enter the taks's id: ");
			Item item = new Item();
			item = tracker.findById(id);
			System.out.println("Name        : " + item.getName());
			System.out.println("Description : " + item.getDescription());
			tracker.delete(item);
		}

		public String info() {
			return String.format("%s, %s", this.key(), "Delete item");
		}
	}

	private static class FindItemById implements UserAction {
		public int key() {
			return 4;
		}

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please, enter the taks's id: ");
			Item item = new Item();
			item = tracker.findById(id);
			System.out.println("Name        : " + item.getName());
			System.out.println("Description : " + item.getDescription());
		}

		public String info() {
			return String.format("%s, %s", this.key(), "Find item by Id");
		}
	}
}