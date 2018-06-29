package ru.job4j;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CountTest {

    @Test
    public void testLineFunctionWhereArgumentFromOneToFive() {
        Function lineFunction = argument -> (double)(2*argument+1);
        Count count = new Count();
        List<Double> listResult = count.diapason(1,5,lineFunction);
        List<Double> listExpected = Arrays.asList(3.0, 5.0, 7.0, 9.0, 11.0);
        assertThat(listResult, is(listExpected));
    }

    @Test
    public void testDoubleFunctionWhereArgumentFromOneToFive() {
        Function doubleFunction = argument -> Math.pow(argument, 2);
        Count count = new Count();
        List<Double> listResult = count.diapason(1,5,doubleFunction);
        List<Double> listExpected = Arrays.asList(1.0, 4.0, 9.0, 16.0, 25.0);
        assertThat(listResult, is(listExpected));
    }

    @Test
    public void testLogFunctionWithRoundWhereArgumentFromOneToFive() {
        Function logFunction = argument -> Math.rint(Math.log(argument));
        Count count = new Count();
        List<Double> listResult = count.diapason(1,5,logFunction);
        List<Double> listExpected = Arrays.asList(0.0, 1.0, 1.0, 1.0, 2.0);
        assertThat(listResult, is(listExpected));
    }

}