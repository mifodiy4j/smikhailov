package ru.job4j;

public abstract class Figure {
	final Cell position;

	public Figure(Cell position) {
		this.position = position;
	}

	/*Метод должен работать так. dist - задают ячейку куда следует пойти. Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
    Если фигура туда пойти не может. выбросить исключение ImposibleMoveException */
	abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

}