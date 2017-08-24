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
public class SquareTest {
	/**
	* Test pic.
	*/
	@Test
	public void whenSquare() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Paint paint = new Paint(new Square());
		paint.draw();
		assertThat(out.toString(),is(String.format("****************%s",System.getProperty("line.separator"))));
		//assertThat(result, is(expect));
	}
}