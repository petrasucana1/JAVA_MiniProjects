package org.example;

public class GameController {
    private int currentIndex;
    private boolean status;

    public GameController() {
        this.currentIndex = 1;
        status=true;
    }

    public synchronized int getCurrentIndex() {
        return currentIndex;
    }

    public synchronized void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
