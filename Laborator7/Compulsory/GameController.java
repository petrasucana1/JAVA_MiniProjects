package org.example;

public class GameController {
    private int currentIndex;

    public GameController() {
        this.currentIndex = 1;
    }

    public synchronized int getCurrentIndex() {
        return currentIndex;
    }

    public synchronized void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
