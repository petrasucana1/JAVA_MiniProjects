package org.example;

public class Sequence {
    private int first;
    private int last;
    private int length;

    public Sequence(int first, int last, int length) {
        this.first = first;
        this.last = last;
        this.length = length;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
