package org.example;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;

public class GameGraph {
    Graph<Token, DefaultEdge> graph;
    private int maxPath;

    public GameGraph() {
        graph = new DirectedMultigraph<>(DefaultEdge.class);
        maxPath = 1;
    }

    public void addVertex(Token token) {
        graph.addVertex(token);
        updateEdges(token);
    }

    public void updateEdges(Token token2) {
        for (Token token1 : graph.vertexSet()) {
            if (token1.getFirst() == token2.getSecond()) {
                graph.addEdge(token2, token1);
            }
            if (token2.getFirst() == token1.getSecond()) {
                graph.addEdge(token1, token2);
            }
        }
    }

    public int findMaxPath() {
        LongestPathFinder maxPath=new LongestPathFinder(graph);
        return maxPath.findLongestPath();
    }

    public boolean containsToken(Token token) {
        for (Token existingToken : graph.vertexSet()) {
            if (existingToken.getFirst() == token.getFirst() && existingToken.getSecond() == token.getSecond()) {
                return true;
            }
        }
        return false;
    }
}
