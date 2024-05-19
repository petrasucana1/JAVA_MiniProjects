package org.example;

public class Player {
    private String id;
    private long timeRemaining; // timpul rămas în milisecunde
    private static final long STARTING_TIME = 600000; //5000// 10 minute

    public Player(String id) {
        this.id = id;
        this.timeRemaining = STARTING_TIME;
    }

    public String getId() {
        return id;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void decreaseTime(long time) {
        this.timeRemaining -= time;
        if (this.timeRemaining < 0) {
            this.timeRemaining = 0;
        }
    }

    public boolean isTimeUp() {
        return timeRemaining <= 0;
    }
}
