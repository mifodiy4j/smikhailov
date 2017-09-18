package ru.job4j;

import ru.job4j.*;

import java.util.*;

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
public class ConvertListTest {
	/**
	* Test convertList.
	*/
   @Test
   public void whenListConvertToArray7Elements() {

       ConvertList convertList = new ConvertList();

       List<Integer> list = new ArrayList<>();
       list.add(1);
       list.add(2);
       list.add(3);
       list.add(4);
       list.add(5);
       list.add(6);
       list.add(7);

       int[][] array = {
               {1, 2, 3},
               {4, 5, 6},
               {7, 0 ,0}
       };

       assertThat(convertList.toArray(list, 3), is(array));

   }

    /**
     * Test convertList.
     */
    @Test
    public void whenListConvertToArray10Elements() {

        ConvertList convertList = new ConvertList();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        int[][] array = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10 ,0, 0}
        };

        assertThat(convertList.toArray(list, 3), is(array));

    }

    /**
     * Test convertList.
     */
    @Test
    public void whenListConvertToArray9Elements() {

        ConvertList convertList = new ConvertList();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);

        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        assertThat(convertList.toArray(list, 3), is(array));

    }

    /**
     * Test convertList.
     */
    @Test
    public void whenArrayConvertToList7Elements() {

        ConvertList convertList = new ConvertList();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7}
        };

        assertThat(convertList.toList(array), is(list));

    }

}