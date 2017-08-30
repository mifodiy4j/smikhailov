package ru.job4j;

public abstract class BaseAction implements UserAction {
	
	private String name;
	public int key;

	public BaseAction(String name, int key) {
		this.name = name;
		this.key = key;
	}

	//public abstract int key();

	//public abstract void execute(Input input, Tracker tracker);

	public String info() {
		return String.format("%s, %s", this.key(), name);
	}
}