package org.example;

public class TimeKeeper implements Runnable{
    private final long timeLimit;
    private final long startTime;
    private GameController status;

    public TimeKeeper(long timeLimit,GameController status){
        this.timeLimit=timeLimit;
        this.startTime=System.currentTimeMillis();
        this.status=status;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(timeLimit);
            System.out.println("Time limit reached. Game over!");
            status.setStatus(false);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
