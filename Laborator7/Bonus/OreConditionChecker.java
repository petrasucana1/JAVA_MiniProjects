package org.example;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;


import java.util.Set;
public class OreConditionChecker {
    public boolean satisfiesOreCondition(Graph<Token, DefaultEdge> graph) {
        int n = graph.vertexSet().size();

        for (Token vertex : graph.vertexSet()) {
            Set<Token> neighbors = Graphs.neighborSetOf(graph, vertex);
            int degreeSum1 = neighbors.size();
            for (Token neighbor : neighbors) {
                int degreeSum2 = Graphs.neighborSetOf(graph, neighbor).size();
                if (degreeSum1+degreeSum2 < n) {
                    return false;
                }
            }

        }

        return true;
    }
}