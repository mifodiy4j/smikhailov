package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Sergey Mikhailov (mailto:mifodiy67@mail.ru)
* @version $Id$
* @since 0.1
*/
public class BoardTest {
    /**
    * Test paint.
    */
	@Test
	public void whenWidthIs3HeightIs3() {
	    Board board1 = new Board();
	    String result1 = board1.paint(3, 3);
	    String expectStr1 = "X X\n X \nX X";
	    assertThat(result1, is(expectStr1));
	}

	/**
    * Test paint.
    */
	@Test
	public void whenWidthIs5HeightIs4() {
	    Board board2 = new Board();
	    String result2 = board2.paint(5, 4);
	    String expectStr2 = "X X X\n X X \nX X X\n X X ";
	    assertThat(result2, is(expectStr2));
	}
}