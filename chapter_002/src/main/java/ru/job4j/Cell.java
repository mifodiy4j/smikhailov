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

	public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            if (((Cell)obj).getString() == this.string &&
                ((Cell)obj).getColumn() == this.column) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    } 
}