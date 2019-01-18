package com.jackie.concurrency.concurrency.practice;

@NotThreadSafe
public class MutablePoint {
    public int x;
    public int y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

}
