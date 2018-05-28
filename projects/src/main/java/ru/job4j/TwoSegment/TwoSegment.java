package ru.job4j.TwoSegment;

public class TwoSegment {
    private Segment ab;
    private Segment cd;

    public TwoSegment(Segment ab, Segment cd) {
        this.ab = ab;
        this.cd = cd;
    }

    public boolean cut() {

        if ((ab.getStart() < cd.getStart()) &&
                (ab.getEnd() > cd.getStart())) {
            return true;
        } else if ((ab.getStart() > cd.getStart()) &&
                (ab.getStart() < cd.getEnd())) {
            return true;
        } else {
            return false;
        }
    }
}
