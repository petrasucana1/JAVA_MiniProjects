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
    GameGraph graph;


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
        this.graph=new GameGraph();

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
                        while(graph.containsToken(token)) {
                            bag.add(token);
                            token = bag.remove(0);
                        }
                        System.out.println(name + " extracted token: (" + token.getFirst() + ", " + token.getSecond() + ")");
                        currentIndex.setCurrentIndex((currentIndex.getCurrentIndex()%numberOfPlayers)+1);
                        graph.addVertex(token);
                        points=graph.findMaxPath();

                        lock.notifyAll();
                    }
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        OreConditionChecker oreTh=new OreConditionChecker();
        if(oreTh.satisfiesOreCondition(graph.graph)){
            HamiltonianCycleFinder HamiltonianGraph=new HamiltonianCycleFinder();
            HamiltonianGraph.findHamiltonianCycle(graph.graph);
        }


    }
}
