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
public class PaintTest {
    /**
    * Test piramid.
    */
	@Test
	public void whenHIs2() {
	    Paint paint1 = new Paint();
	    String result1 = paint1.piramid(2);
	    String expectStr1 = " ^ ^^^";
	    assertThat(result1, is(expectStr1));
	}

	/**
    * Test piramid.
    */
	@Test
	public void whenHIs3() {
	    Paint paint2 = new Paint();
	    String result2 = paint2.piramid(3);
	    String expectStr2 = "  ^   ^^^ ^^^^^";
	    assertThat(result2, is(expectStr2));
	}
}