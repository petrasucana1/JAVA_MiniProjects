package org.example;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, Character[][]> playerBoards;
    private Map<String, Integer> playerShips;
    private final int SIZE = 10;
    private final int TOTAL_SHIPS = 4;

    public Board() {
        playerBoards = new HashMap<>();
        playerShips = new HashMap<>();
    }

    public void addPlayer(Player player) {
        Character[][] board = new Character[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '~'; // '~' represents water
            }
        }
        playerBoards.put(player.getId(), board);
        playerShips.put(player.getId(), 0); // initialize ship count
    }

    public String placeShip(Player player, int x, int y) {
        Character[][] board = playerBoards.get(player.getId());
        if (board[x][y] == 'S') {
            return "Already placed a ship here.";
        }
        if (playerShips.get(player.getId()) >= TOTAL_SHIPS) {
            return "All ships already placed.";
        }
        board[x][y] = 'S'; // 'S' reprezintă o barcă
        playerShips.put(player.getId(), playerShips.get(player.getId()) + 1);
        if (playerShips.get(player.getId()) == TOTAL_SHIPS) {
            return "All ships placed.";
        }
        return "Ship placed at (" + x + ", " + y + ")";
    }


    public String applyMove(Player player, Move move) {
        Character[][] board = playerBoards.get(player.getId());
        if (board[move.getX()][move.getY()] == '~') {
            board[move.getX()][move.getY()] = 'M'; // 'M' represents a miss
            return "Move applied: Miss!";
        } else if (board[move.getX()][move.getY()] == 'S') {
            board[move.getX()][move.getY()] = 'H'; // 'H' represents a hit
            return "Move applied: Hit!";
        } else {
            return "Invalid move: Already targeted this location.";
        }
    }

    public boolean areAllShipsPlaced() {
        return playerShips.values().stream().allMatch(count -> count == TOTAL_SHIPS);
    }

    public boolean allShipsSunk(Player player) {
        Character[][] board = playerBoards.get(player.getId());
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 'S') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasPlayerLost(Player player) {
        Character[][] board = playerBoards.get(player.getId());
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 'S') {
                    return false;
                }
            }
        }
        return true;
    }
}
