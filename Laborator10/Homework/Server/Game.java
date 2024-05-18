package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int id;
    private List<Player> players;
    private Board board;
    private boolean isStarted;
    private boolean allShipsPlaced;
    private int currentPlayerIndex;
    private long moveTime; // Timpul alocat pentru fiecare mișcare

    public Game(int id, long moveTime) {
        this.id = id;
        this.moveTime = moveTime;
        players = new ArrayList<>();
        board = new Board();
        isStarted = false;
        allShipsPlaced = false;
        currentPlayerIndex = 0;
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void switchPlayerTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public String submitMove(String playerId, Move move) {
        if (!isStarted || !allShipsPlaced) {
            return "Game has not started yet.";
        }

        Player player = players.stream().filter(p -> p.getId().equals(playerId)).findFirst().orElse(null);
        if (player == null) {
            return "Invalid player";
        }

        if (!getCurrentPlayer().equals(player)) {
            return "It's not your turn.";
        }

        if (player.isTimeUp()) {
            isStarted = false;
            return "Time's up! You lost the game. Game over.";
        }

        player.decreaseTime(moveTime); // Scădem timpul pentru mișcare

        Player opponent = players.stream().filter(p -> !p.getId().equals(playerId)).findFirst().orElse(null);
        String response = board.applyMove(opponent, move);

        if (response.startsWith("Invalid move")) {
            return response;
        }

        if (board.hasPlayerLost(opponent)) {
            isStarted = false;
        }

        switchPlayerTurn();

        return response;
    }

    public int getId() {
        return id;
    }

    public String addPlayer() {
        if (players.size() < 2) {
            Player player = new Player("Player" + (players.size() + 1));
            players.add(player);
            board.addPlayer(player);
            if (players.size() == 2) {
                return player.getId() + " joined the game. The game is starting!";
            } else {
                return player.getId() + " joined the game. Waiting for another player.";
            }
        } else {
            return "Game is full";
        }
    }

    public String placeShip(String playerId, int x, int y) {
        if (isStarted) {
            return "Game already started.";
        }
        Player player = players.stream().filter(p -> p.getId().equals(playerId)).findFirst().orElse(null);
        if (player == null) {
            return "Invalid player";
        }
        String response = board.placeShip(player, x, y);
        if (board.areAllShipsPlaced()) {
            allShipsPlaced = true;
            isStarted = true;
        }
        return response;
    }


    public boolean isGameOver() {
        return players.stream().anyMatch(player -> board.allShipsSunk(player));
    }



}
