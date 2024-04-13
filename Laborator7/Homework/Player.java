package org.example;

import java.util.*;

public class Player implements Runnable{
    private final String name;
    private final List<Token> bag;
    private int numberOfPlayers;
    private final int maxSequence;
    private List<Sequence> playerTokens;
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
        this.playerTokens= new ArrayList<>();
        points=1;
        this.currentIndex=currentIndex;
    }

    public int getPoints(){
        return points;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while(points< maxSequence && !bag.isEmpty() && currentIndex.getStatus()){
            synchronized (lock){
                try{
                    while(currentIndex.getCurrentIndex() != playerIndex && !bag.isEmpty() && currentIndex.getStatus()){
                        lock.wait();
                    }
                    if(!bag.isEmpty()){
                        Token token =bag.remove(0);
                        System.out.println(name + " extracted token: (" + token.getFirst() + ", " + token.getSecond() + ")");
                        currentIndex.setCurrentIndex((currentIndex.getCurrentIndex()%numberOfPlayers)+1);

                        int max=0;
                        int first=0;
                        int second=0;
                        for(Sequence seq: playerTokens){
                            if(seq.getFirst()== token.getSecond())
                                if(seq.getLength()>max){
                                    first=seq.getFirst();
                                    second=seq.getLast();
                                    max=seq.getLength();
                                }
                            if(seq.getLast() == token.getFirst())
                                if(seq.getLength()>max){
                                    first=seq.getFirst();
                                    second=seq.getLast();
                                    max=seq.getLength();
                                }
                        }
                        if(max==0) {
                            playerTokens.add(new Sequence(token.getFirst(), token.getSecond(), 1));
                        }else{
                            for(Sequence seq:playerTokens){
                                if(seq.getFirst()==first && seq.getLast()==second && seq.getLength()==max){
                                    if(seq.getFirst()== token.getSecond()){
                                        seq.setFirst(token.getFirst());
                                        seq.setLength(max+1);
                                    }
                                    if(seq.getLast() == token.getFirst()){
                                        seq.setLast(token.getSecond());
                                        seq.setLength(max+1);
                                    }
                                    if(max+1 >points)
                                        points++;
                                    break;
                                }

                            }
                        }

                        lock.notifyAll();
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
