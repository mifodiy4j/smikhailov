package ru.job4j;

public class Bishop extends Figure {

	Cell[] cellArray;
	private int changeString, changeColumn, lengthCellArray;

	public Bishop (Cell position) {
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

			if (Math.abs(changeString) == Math.abs(changeColumn)) 
				newPositionValid = true;
		} else {
			throw new ImpossibleMoveException("Out of board rage.");
		}

		if (newPositionValid) {
			lengthCellArray = Math.abs(changeString) - 1;
			cellArray = new Cell[lengthCellArray];

			int nextString = position.getString();
			int nextColumn = position.getColumn();

			for (int i = 0; i < lengthCellArray; i++) {
				nextString = nextString + changeString/Math.abs(changeString);
				nextColumn = nextColumn + changeColumn/Math.abs(changeColumn);
				cellArray[i] = new Cell(nextString, nextColumn);
			}

		} else {
			throw new ImpossibleMoveException("Invalid position on board, this figure has another move.");
		}

		return cellArray;
	}
}