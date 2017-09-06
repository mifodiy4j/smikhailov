package ru.job4j;

public class Board{
	Figure[] figures;

	private boolean findFigure = false;
	private Figure figureMove;
	private Cell[] cellArrayMove;
	private boolean hasFigureOnWay = false;

	public Board (Figure[] figures) {
		this.figures = figures;
	}

	boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException { 
		
		for (Figure figure : figures) {
			if ((figure.position).equals(source)) {
				findFigure = true;
				figureMove = figure;
				throw new FigureNotFoundException("Figure is not find.");
			}
		}

		if (findFigure) {
			throw new FigureNotFoundException("Figure is not find.");
		}

		cellArrayMove = figureMove.way(dist);
		
		for (Figure figure : figures) {
			for (Cell cell : cellArrayMove) {
				if ((figure.position).equals(cell)) {
					hasFigureOnWay = true;
				}
			}
		}

		if ((hasFigureOnWay) && !(figureMove instanceof Knight)) {
			throw new OccupiedWayException("Occupied way.");
		}

		//Figure figure.clone(Cell dist);
		return true;
	}

}