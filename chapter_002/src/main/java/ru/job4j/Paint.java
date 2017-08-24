package ru.job4j;

/**
 * Paint.
 *
 *@author smikhailov
 *@since 24.08.2017
 *@version 1
 */
public class Paint {
	private Shape shape;

	public Paint(Shape shape) {
		this.shape = shape;
	}

	public void draw(){
		System.out.println(shape.pic());
	}
}