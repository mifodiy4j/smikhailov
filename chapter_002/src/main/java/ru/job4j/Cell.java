package ru.job4j;

public class Cell {
	private int string;
	private int column;

	public Cell (int string, int column) {
		this.string = string;
		this.column = column;
	}

	public int getString() {
		return this.string;
	}

	public int getColumn() {
		return this.column;
	}
}