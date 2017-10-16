package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by SERG on 16.10.2017.
 */
public class FindCycleTest {

    @Test
    public void whenListHasCycle() {
        FindCycle findCycle = new FindCycle();
        FindCycle.Node first = new FindCycle.Node(1);
        FindCycle.Node two = new FindCycle.Node(2);
        FindCycle.Node third = new FindCycle.Node(3);
        FindCycle.Node four = new FindCycle.Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        boolean result = findCycle.hasCycle(first);

        assertThat(result, is(true));
    }

    @Test
    public void whenListHasNotCycle() {
        FindCycle findCycle = new FindCycle();
        FindCycle.Node first = new FindCycle.Node(1);
        FindCycle.Node two = new FindCycle.Node(2);
        FindCycle.Node third = new FindCycle.Node(3);
        FindCycle.Node four = new FindCycle.Node(4);
        FindCycle.Node five = new FindCycle.Node(5);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = five;

        boolean result = findCycle.hasCycle(first);

        assertThat(result, is(false));
    }

    @Test
    public void whenListHasCycleInTheMiddle() {
        FindCycle findCycle = new FindCycle();
        FindCycle.Node first = new FindCycle.Node(1);
        FindCycle.Node two = new FindCycle.Node(2);
        FindCycle.Node third = new FindCycle.Node(3);
        FindCycle.Node four = new FindCycle.Node(4);
        FindCycle.Node five = new FindCycle.Node(5);

        first.next = two;
        two.next = third;
        third.next = two;
        four.next = five;

        boolean result = findCycle.hasCycle(first);

        assertThat(result, is(true));
    }
}