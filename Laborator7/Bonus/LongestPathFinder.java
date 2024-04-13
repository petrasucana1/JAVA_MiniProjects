package org.example;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;

public class LongestPathFinder {
    private Graph<Token, DefaultEdge> graph;
    private Set<Token> visited;
    private int maxLength;

    public LongestPathFinder(Graph<Token, DefaultEdge> graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.maxLength = 0;
    }

    public int findLongestPath() {
        for (Token vertex : graph.vertexSet()) {
            if (!visited.contains(vertex)) {
                dfs(vertex, 1);
            }
        }
        return maxLength;
    }

    private void dfs(Token current, int length) {
        visited.add(current);
        maxLength = Math.max(maxLength, length);
        for (DefaultEdge edge : graph.outgoingEdgesOf(current)) {
            Token next = graph.getEdgeTarget(edge);
            if (!visited.contains(next)) {
                dfs(next, length + 1);
            }
        }
        visited.remove(current);
    }
}
