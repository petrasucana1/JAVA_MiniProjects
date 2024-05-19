package org.example;

import java.util.concurrent.ConcurrentHashMap;

public class GameManager {
    private ConcurrentHashMap<Integer, Game> games;
    private int nextGameId;
    private TournamentManager tournamentManager;

    public GameManager() {
        games = new ConcurrentHashMap<>();
        nextGameId = 1;
        int maxGamesPerDay = 3;
        int maxDays = 10;
        tournamentManager = new TournamentManager(maxGamesPerDay, maxDays);
    }

    public synchronized String processRequest(String request) {
        String[] parts = request.split(" ");
        String command = parts[0];

        switch (command) {
            case "create":
                return createGame();
            case "join":
                if (parts.length < 2) {
                    return "Error: Missing gameId for join command";
                }
                return joinGame(Integer.parseInt(parts[1]));
            case "placeShip":
                if (parts.length < 5) {
                    return "Error: Missing parameters for placeShip command";
                }
                return placeShip(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            case "move":
                if (parts.length < 5) {
                    return "Error: Missing parameters for move command";
                }
                return submitMove(Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            case "register":
                if (parts.length < 2) {
                    return "Error: Missing playerId for register command";
                }
                return registerPlayer(parts[1]);
            case "create_tournament":
                return tournamentManager.createTournament();
            case "schedule":
                return tournamentManager.getSchedule();
            case "results":
                return tournamentManager.getResults();
            case "winning_sequence":
                return tournamentManager.findWinningSequence();
            default:
                return "Unknown command";
        }
        }
        private String registerPlayer(String playerId) {
                tournamentManager.registerPlayer(playerId);
                return "Player registered: " + playerId;
            }

        private String createGame() {
            // Specificați timpul alocat pentru mișcare (10 minute în milisecunde)
            long moveTime = 600000;//5000
            Game game = new Game(nextGameId++, moveTime);
            games.put(game.getId(), game);
            return "Game created with ID: " + game.getId();
        }


        private String joinGame(int gameId) {
            Game game = games.get(gameId);
            if (game == null) {
                return "Game not found";
            }
            return game.addPlayer();
        }

        private String placeShip(int gameId, String playerId, int x, int y) {
            Game game = games.get(gameId);
            if (game == null) {
                return "Game not found";
            }
            return game.placeShip(playerId, x, y);
        }


        private String submitMove(int gameId, String playerId, int x, int y) {
            Game game = games.get(gameId);
            if (game == null) {
                return "Game not found";
            }
            String result = game.submitMove(playerId, new Move(x, y));
            if (game.isGameOver()) {
                return "Game Over!";
            }
            return result;
        }
}
