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
public class FactorialTest {
    /**
    * Test calc.
    */
	@Test
	public void when5() {
	    Factorial factorial1 = new Factorial();
	    int result1 = factorial1.calc(5);
	    assertThat(result1, is(120));
	}

	/**
    * Test calc.
    */
	@Test
	public void when0() {
	    Factorial factorial2 = new Factorial();
	    int result2 = factorial2.calc(0);
	    assertThat(result2, is(1));
	}
}