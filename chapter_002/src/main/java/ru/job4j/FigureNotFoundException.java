package ru.job4j;

public class FigureNotFoundException extends RuntimeException {
	
	public FigureNotFoundException(String msg) {
		super(msg);
	}
}