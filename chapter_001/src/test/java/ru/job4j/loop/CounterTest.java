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
public class CounterTest {
    /**
    * Test add.
    */
	@Test
	public void whenFirstIs1SecondIs10() {
	    Counter counter = new Counter();
	    int result = counter.add(1, 10);
	    assertThat(result, is(30));
	}
}