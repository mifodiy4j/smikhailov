package ru.job4j.condition;

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
public class PointTest {
    /**
    * Test is.
    */
	@Test
	public void whenPointIsFunction() {
	    Point point1 = new Point(1, 2);
	    boolean result1 = point1.is(1, 1);
	    assertThat(result1, is(true));
	}

	/**
    * Test is.
    */
	@Test
	public void whenPointIsNotFunction() {
	    Point point2 = new Point(2, 4);
	    boolean result2 = point2.is(2, 1);
	    assertThat(result2, is(false));
	}
}