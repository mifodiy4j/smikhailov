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
public class ArrayDuplicateTest {
    /**
    * Test remove.
    */
    @Test
    public void whenArrayHas2Duplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] mas = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] resultArray = arrayDuplicate.remove(mas);
        String[] expectArray = {"Привет", "Мир", "Супер"};
        assertThat(resultArray, is(expectArray));
    }

}