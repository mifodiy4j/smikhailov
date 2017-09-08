package ru.job4j;

public class Pawn extends Figure {

	Cell[] cellArray;
	private int changeString, changeColumn, lengthCellArray;

	public Pawn (Cell position) {
		super(position);
	}

	/*Метод должен работать так. dist - задают ячейку куда следует пойти. Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
    Если фигура туда пойти не может. выбросить исключение ImposibleMoveException */
	public Cell[] way(Cell dist) throws ImpossibleMoveException {

		boolean newPositionInBoard = false;
		boolean newPositionValid = false;

		if ((dist.getString() < 8) && (dist.getString() >= 0) && (dist.getColumn() < 8) && (dist.getColumn() >= 0)) {
			newPositionInBoard = true;
		}

		if (newPositionInBoard) {

			changeString = dist.getString() - position.getString();
			changeColumn = dist.getColumn() - position.getColumn();

			if (changeString == 1) 
				newPositionValid = true;

		} else {
			throw new ImpossibleMoveException("Out of board rage.");
		}

		if (newPositionValid) {
			cellArray = new Cell[1];

			cellArray[0] = new Cell(position.getString(), position.getColumn());

		} else {
			throw new ImpossibleMoveException("Invalid position on board, this figure has another move.");
		}

		return cellArray;
	}

	void clone(Cell position) {
		new Pawn(position);
	}
}