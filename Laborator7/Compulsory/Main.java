package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       int numberOfPlayers=3;
       int numberOfTokens=15;
       int maxSequence=10;

       List<Token> bag= generateTokens(numberOfTokens,maxSequence);

       System.out.println();
       System.out.println("\u001B[44m \033[1;30m The bag has the following tokens: \033[0m\u001B[0m");
       for(Token token:bag)
           System.out.print("("+token.getFirst()+", "+token.getSecond()+")  ");

       Object lock=new Object();

       System.out.println();
       System.out.println();
       System.out.println("\u001B[42m \033[1;30m Players extracting tokens from bag: \033[0m\u001B[0m");
       play(numberOfPlayers,bag, maxSequence,lock);


    }

    private static List<Token> generateTokens(int numberOfTokens, int maxSequence){
        List<Token> bag=new ArrayList<>();
        Random random=new Random();

        for(int i=1;i<=numberOfTokens;i++){
            int first=0;
            int second=0;
            while(first==second) {
                first = random.nextInt(maxSequence) + 1;
                second = random.nextInt(maxSequence) + 1;
            }
            bag.add(new Token(first,second));
        }

        return bag;
    }

    private static void play(int numberOfPlayers, List<Token> bag, int maxSequence, Object lock){
        List<Thread> playerThreads= new ArrayList<>();
        GameController currentIndex=new GameController();

        for(int i=1;i<=numberOfPlayers;i++){
            Player player= new Player("Player "+i, bag,numberOfPlayers, maxSequence,i,lock,currentIndex);
            Thread playerThread= new Thread(player);
            playerThreads.add(playerThread);
            playerThread.start();
        }

        //Wait for all player threads to finish
        for(Thread playerThread: playerThreads){
            try{
                playerThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}