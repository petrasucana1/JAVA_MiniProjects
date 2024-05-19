package org.example;

import java.util.*;

public class TournamentManager {
    private List<Player> players;
    private int maxGamesPerDay;
    private int maxDays;
    private Map<Integer, List<Game>> schedule;
    private Map<String, String> results;

    public TournamentManager(int maxGamesPerDay, int maxDays) {
        this.players = new ArrayList<>();
        this.maxGamesPerDay = maxGamesPerDay;
        this.maxDays = maxDays;
        this.schedule = new HashMap<>();
        this.results = new HashMap<>();
    }

    public void registerPlayer(String playerId) {
        players.add(new Player(playerId));
    }

    public String createTournament() {
        if (players.size() < 2) {
            return "Not enough players to create a tournament";
        }

        generateSchedule();
        generateResults();

        return "Tournament created successfully";
    }

    private void generateSchedule() {
        Random random = new Random();
        List<Game> games = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Game game = new Game(i * players.size() + j, 60000);
                game.addPlayer(players.get(i));
                game.addPlayer(players.get(j));
                games.add(game);
            }
        }

        Collections.shuffle(games, random);

        int day = 1;
        int gamesPerDay = 0;
        for (Game game : games) {
            if (gamesPerDay == maxGamesPerDay) {
                day++;
                gamesPerDay = 0;
            }
            if (day > maxDays) {
                throw new IllegalStateException("Cannot schedule all games within the given days");
            }
            gamesPerDay++;
            schedule.computeIfAbsent(day, k -> new ArrayList<>()).add(game);
        }
    }

    private void generateResults() {
        Random random = new Random();
        for (List<Game> games : schedule.values()) {
            for (Game game : games) {
                Player player1 = game.getPlayers().get(0);
                Player player2 = game.getPlayers().get(1);
                boolean player1Wins = random.nextBoolean();
                Player winner = player1Wins ? player1 : player2;
                Player loser = player1Wins ? player2 : player1;
                results.put(winner.getId() + "-" + loser.getId(), winner.getId() + " beats " + loser.getId());
            }
        }
    }

    public String getSchedule() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Game>> entry : schedule.entrySet()) {
            sb.append("Day ").append(entry.getKey()).append(":\n");
            for (Game game : entry.getValue()) {
                sb.append("  Game ").append(game.getId()).append(": ")
                        .append(game.getPlayers().get(0).getId()).append(" vs ")
                        .append(game.getPlayers().get(1).getId()).append("\n");
            }
        }
        return sb.toString();
    }

    public String getResults() {
        StringBuilder sb = new StringBuilder();
        for (String result : results.values()) {
            sb.append(result).append("\n");
        }
        return sb.toString();
    }

    public String findWinningSequence() {
        Map<String, List<String>> graph = new HashMap<>();
        for (String result : results.values()) {
            String[] parts = result.split(" beats ");
            graph.computeIfAbsent(parts[0], k -> new ArrayList<>()).add(parts[1]);
        }

        //System.out.println("Graph: " + graph);

        List<String> sequence = findSequenceIgnoringCycles(graph);
        if (sequence == null) {
            return "No valid winning sequence found";
        }
        return String.join(" -> ", sequence);
    }

    private List<String> findSequenceIgnoringCycles(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        List<String> sequence = new ArrayList<>();
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(graph, node, visited, sequence);
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private void dfs(Map<String, List<String>> graph, String node, Set<String> visited, List<String> sequence) {
        visited.add(node);
        if (graph.containsKey(node)) {
            for (String neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    dfs(graph, neighbor, visited, sequence);
                }
            }
        }
        sequence.add(node);
    }

}