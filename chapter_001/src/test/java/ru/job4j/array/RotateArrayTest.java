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
public class RotateArrayTest {
    /**
    * Test rotate.
    */
    @Test
    public void whenArrayIsSquare2() {
        RotateArray rotateArray1 = new RotateArray();
        int[][] mas1 = {    {1, 2},
                            {3, 4}  };
        int[][] resultArray1 = rotateArray1.rotate(mas1);
        int[][] expectArray1 = {    {3, 1},
                                    {4, 2}  };
        assertThat(resultArray1, is(expectArray1));
    }

    /**
    * Test rotate.
    */
    @Test
    public void whenArrayIsSquare3() {
        RotateArray rotateArray2 = new RotateArray();
        int[][] mas2 = {    {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}  };
        int[][] resultArray2 = rotateArray2.rotate(mas2);
        int[][] expectArray2 = {    {7, 4, 1},
                                    {8, 5, 2},
                                    {9, 6, 3}  };
        assertThat(resultArray2, is(expectArray2));
    }

}