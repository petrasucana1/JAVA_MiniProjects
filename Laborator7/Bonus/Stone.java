package org.example.game;

public class Stone {//extends Node {

    private String color; // "red" or "blue"

    public Stone(String color) {
        //super(x,y);
        this.color = color;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
