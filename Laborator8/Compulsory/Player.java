package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable{
    private final String name;
    private final List<Token> bag;
    private int numberOfPlayers;
    private final int maxSequence;
    private List<Token> playerTokens;
    private int points;

    private final Object lock;

    private GameController currentIndex;
    private int playerIndex;


    public Player(String name, List<Token> bag,int numberOfPlayers, int maxSequence, int playerIndex, Object lock, GameController currentIndex){
        this.name=name;
        this.bag=bag;
        this.numberOfPlayers=numberOfPlayers;
        this.maxSequence=maxSequence;
        this.playerIndex=playerIndex;
        this.lock=lock;
        this.playerTokens=new ArrayList<>();
        points=0;
        this.currentIndex=currentIndex;
    }

    public int getPoints(){
        return points;
    }

    @Override
    public void run() {
        while(points< maxSequence && !bag.isEmpty()){
            synchronized (lock){
                try{
                    while(currentIndex.getCurrentIndex() != playerIndex && !bag.isEmpty()){
                        lock.wait();
                    }
                    if(!bag.isEmpty()){
                        Token token =bag.remove(0);
                        System.out.println(name + " extracted token: (" + token.getFirst() + ", " + token.getSecond() + ")");
                        currentIndex.setCurrentIndex((currentIndex.getCurrentIndex()%numberOfPlayers)+1);
                        points++;
                        lock.notifyAll();
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
