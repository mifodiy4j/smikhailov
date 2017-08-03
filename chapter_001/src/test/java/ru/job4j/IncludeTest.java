package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Sergey Mikhailov (mailto:mifodiy67@mail.ru)
* @version $Id$
* @since 0.1
*/
public class IncludeTest {
	/**
	* Test contains.
	*/
	@Test
	public void whenThreeOnSix() {
		Include include = new Include();
		String origin = "Привет";
		String sub = "иве";
	    boolean result = include.contains(origin, sub);
	    boolean expect = true;
	    assertThat(result, is(expect));
	}

	/**
	* Test contains.
	*/
	@Test
	public void whenThreeNotOnSix() {
		Include include1 = new Include();
		String origin1 = "Привет";
		String sub1 = "Пив";
	    boolean result1 = include1.contains(origin1, sub1);
	    boolean expect1 = false;
	    assertThat(result1, is(expect1));
	}
}