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
public class CodeDepartmentTest {
	/**
	* Test add.
	*/
   @Test
   public void whenAdd7Elements() {
       CodeDepartment codeDepartment = new CodeDepartment();

       codeDepartment.add("K1\\SK1");
       codeDepartment.add("K1\\SK2");
       codeDepartment.add("K1\\SK1\\SSK1");
       codeDepartment.add("K1\\SK1\\SSK2");
       codeDepartment.add("K2");
       codeDepartment.add("K2\\SK1\\SSK1");
       codeDepartment.add("K2\\SK1\\SSK2");

       TreeSet<String> result = codeDepartment.departments;

       TreeSet<String> expectation = new TreeSet<>();
       expectation.add("K1");
       expectation.add("K1\\SK1");
       expectation.add("K1\\SK1\\SSK1");
       expectation.add("K1\\SK1\\SSK2");
       expectation.add("K1\\SK2");
       expectation.add("K2");
       expectation.add("K2\\SK1");
       expectation.add("K2\\SK1\\SSK1");
       expectation.add("K2\\SK1\\SSK2");

       assertThat(result, is(expectation));
   }

    /**
     * Test add.
     */
    @Test
    public void whenAdd7ElementsForReverse() {
        CodeDepartment codeDepartment = new CodeDepartment();

        codeDepartment.add("K1\\SK1");
        codeDepartment.add("K1\\SK2");
        codeDepartment.add("K1\\SK1\\SSK1");
        codeDepartment.add("K1\\SK1\\SSK2");
        codeDepartment.add("K2");
        codeDepartment.add("K2\\SK1\\SSK1");
        codeDepartment.add("K2\\SK1\\SSK2");

        TreeSet<String> result = codeDepartment.departmentsReverse;

        TreeSet<String> expectation = new TreeSet<>();
        expectation.add("K2");
        expectation.add("K2\\SK1");
        expectation.add("K2\\SK1\\SSK2");
        expectation.add("K2\\SK1\\SSK1");
        expectation.add("K1");
        expectation.add("K1\\SK2");
        expectation.add("K1\\SK1");
        expectation.add("K1\\SK1\\SSK2");
        expectation.add("K1\\SK1\\SSK1");

        assertThat(result, is(expectation));
    }
}