package org.example;

import java.sql.Time;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
       int numberOfPlayers=3;
       int numberOfTokens=15;
       int maxSequence=10;
       long timeLimit=30000;

       List<Token> bag= generateTokens(numberOfTokens,maxSequence);

       System.out.println();
       System.out.println("\u001B[44m \033[1;30m The bag has the following tokens: \033[0m\u001B[0m");
       for(Token token:bag)
           System.out.print("("+token.getFirst()+", "+token.getSecond()+")  ");

       Object lock=new Object();

       System.out.println();
       System.out.println();
       System.out.println("\u001B[42m \033[1;30m Players extracting tokens from bag: \033[0m\u001B[0m");
       play(numberOfPlayers,bag, maxSequence,lock,timeLimit);


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

    private static void play(int numberOfPlayers, List<Token> bag, int maxSequence, Object lock, long timeLimit){

        List<Thread> playerThreads= new ArrayList<>();
        List<Player> players=new ArrayList<>();
        GameController currentIndex=new GameController();

        for(int i=1;i<=numberOfPlayers;i++){
            Player player= new Player("Player "+i, bag,numberOfPlayers, maxSequence,i,lock,currentIndex);
            players.add(player);
            Thread playerThread= new Thread(player);
            playerThreads.add(playerThread);
            playerThread.start();
        }

        TimeKeeper timeKeeper=new TimeKeeper(timeLimit,currentIndex);
        Thread timeKeeperThread= new Thread(timeKeeper);
        timeKeeperThread.setDaemon(true);
        timeKeeperThread.start();


        //Wait for all player threads to finish
        for(Thread playerThread: playerThreads){
            try{
                playerThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        ///set the winner
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getPoints(), p1.getPoints()); // Sortare descrescătoare
            }
        });


        System.out.println();
        int prize=1;
        int score= players.get(0).getPoints();
        for (Player player : players) {
            if(player.getPoints()!=score) {
                score = player.getPoints();
                prize++;
            }
            if(prize==4)
                break;
            System.out.print("\u001B[44m \033[1;30m "+prize +" Place: \033[0m\u001B[0m");
            System.out.println(player.getName() + " - " + player.getPoints() + " points");
        }
    }

}