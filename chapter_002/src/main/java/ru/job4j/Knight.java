package ru.job4j;

public class Knight extends Figure {

	Cell[] cellArray;
	private int changeString, changeColumn, lengthCellArray;

	public Knight (Cell position) {
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

			if (Math.abs(changeString) == 2 * Math.abs(changeColumn)) 
				newPositionValid = true;

			if (Math.abs(changeColumn) == 2 * Math.abs(changeString)) 
				newPositionValid = true;

		} else {
			throw new ImpossibleMoveException("Out of board rage.");
		}

		if (newPositionValid) {
			cellArray = new Cell[6];

			int nextString = position.getString();
			int nextColumn = position.getColumn();

			if (Math.abs(changeString) > Math.abs(changeColumn)) {
				cellArray[0] = new Cell(nextString, nextColumn);
				cellArray[1] = new Cell(nextString, nextColumn + changeColumn);
				cellArray[2] = new Cell(nextString + changeString/2, nextColumn);
				cellArray[3] = new Cell(nextString + changeString/2, nextColumn + changeColumn);
				cellArray[4] = new Cell(nextString + changeString, nextColumn);
				cellArray[5] = new Cell(nextString + changeString, nextColumn + changeColumn);
			} else {
				cellArray[0] = new Cell(nextString, nextColumn);
				cellArray[1] = new Cell(nextString + changeString, nextColumn);
				cellArray[2] = new Cell(nextString, nextColumn + changeColumn/2);
				cellArray[3] = new Cell(nextString + changeString, nextColumn + changeColumn/2);
				cellArray[4] = new Cell(nextString, nextColumn + changeColumn);
				cellArray[5] = new Cell(nextString + changeString, nextColumn + changeColumn);
			}

		} else {
			throw new ImpossibleMoveException("Invalid position on board, this figure has another move.");
		}

		return cellArray;
	}

	void clone(Cell position) {
		new Knight(position);
	}
}