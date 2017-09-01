package ru.job4j;

public abstract class Figure {
	final Cell position;

	Figure(Cell position) {
		this.position = position;
	}

	abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}