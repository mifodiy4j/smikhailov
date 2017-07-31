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
public class TurnTest {
    /**
    * Test back.
    */
    @Test
    public void whenArrayLengthIs5() {
        Turn turn1 = new Turn();
        int[] mas1 = {1, 2, 3, 4, 5};
        int[] resultArray1 = turn1.back(mas1);
        int[] expectArray1 = {5, 4, 3, 2, 1};
        assertThat(resultArray1, is(expectArray1));
    }

    /**
    * Test back.
    */
    @Test
    public void whenArrayLengthIs4() {
        Turn turn2 = new Turn();
        int[] mas2 = {4, 1, 6, 2};
        int[] resultArray2 = turn2.back(mas2);
        int[] expectArray2 = {2, 6, 1, 4};
        assertThat(resultArray2, is(expectArray2));
    }
}