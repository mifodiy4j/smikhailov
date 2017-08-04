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
public class MergeTest {
	/**
	* Test mergeArrays.
	*/
	@Test
	public void mergeArrayOneLessThenArrayTwo() {
		Merge merge = new Merge();
		int[] mas1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] mas2 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	    int[] result = merge.mergeArrays(mas1, mas2);
	    int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	    assertThat(result, is(expect));
	}

	/**
	* Test mergeArrays.
	*/
	@Test
	public void mergeArrayOneLargerThenArrayTwo() {
		Merge merge = new Merge();
		int[] mas1 = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		int[] mas2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	    int[] result = merge.mergeArrays(mas1, mas2);
	    int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	    assertThat(result, is(expect));
	}

	/**
	* Test mergeArrays.
	*/
	@Test
	public void mergeArrayOneIsOddArrayTwoIsEven() {
		Merge merge = new Merge();
		int[] mas1 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
		int[] mas2 = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
	    int[] result = merge.mergeArrays(mas1, mas2);
	    int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
	    assertThat(result, is(expect));
	}

}