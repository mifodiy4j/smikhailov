package ru.job4j.array;

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
public class BubbleTest {
    /**
    * Test sort.
    */
    @Test
    public void whenArrayLengthIs5() {
        Bubble bubble = new Bubble();
        int[] mas = {5, 1, 2, 7, 3};
        int[] resultArray = bubble.sort(mas);
        int[] expectArray = {1, 2, 3, 5, 7};
        assertThat(resultArray, is(expectArray));
    }

}