package ru.job4j.TwoSegment;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TwoSegmentTest {

    @Test
    public void whenALessCAndBLessC() {
        Segment ab = new Segment(3, 5);
        Segment cd = new Segment(7, 10);
        TwoSegment twoSegment = new TwoSegment(ab, cd);

        boolean actual = twoSegment.cut();
        boolean expected = false;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenALessCAndBMoreCButLessD() {
        Segment ab = new Segment(3, 8);
        Segment cd = new Segment(7, 10);
        TwoSegment twoSegment = new TwoSegment(ab, cd);

        boolean actual = twoSegment.cut();
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenALessCAndBMoreD() {
        Segment ab = new Segment(3, 12);
        Segment cd = new Segment(7, 10);
        TwoSegment twoSegment = new TwoSegment(ab, cd);

        boolean actual = twoSegment.cut();
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAMoreCAndBLessD() {
        Segment ab = new Segment(8, 9);
        Segment cd = new Segment(7, 10);
        TwoSegment twoSegment = new TwoSegment(ab, cd);

        boolean actual = twoSegment.cut();
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAMoreCAndBMoreD() {
        Segment ab = new Segment(8, 12);
        Segment cd = new Segment(7, 10);
        TwoSegment twoSegment = new TwoSegment(ab, cd);

        boolean actual = twoSegment.cut();
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void whenAMoreD() {
        Segment ab = new Segment(12, 13);
        Segment cd = new Segment(7, 10);
        TwoSegment twoSegment = new TwoSegment(ab, cd);

        boolean actual = twoSegment.cut();
        boolean expected = false;

        assertThat(actual, is(expected));
    }

}