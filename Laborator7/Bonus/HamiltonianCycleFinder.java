package org.example;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HamiltonianCycleFinder {

    public void findHamiltonianCycle(Graph<Token, DefaultEdge> graph) {
        List<Token> cycle = new ArrayList<>();

        // Alege un nod de plecare
        Token startNode = graph.vertexSet().iterator().next();

        // Adaugă nodul de plecare la ciclu
        cycle.add(startNode);

        // Explorează graful pentru a găsi un ciclu hamiltonian
        exploreGraph(graph, startNode, cycle);

        // Verifică dacă s-a găsit un ciclu hamiltonian
        if (cycle.size() == graph.vertexSet().size() && graph.containsEdge(cycle.get(cycle.size() - 1), startNode)) {
            System.out.println();
            System.out.println("\u001B[44m \033[1;30m Graful jucatorului este hamiltonian: \033[0m\u001B[0m");
            for(Token token:cycle)
                System.out.print("("+token.getFirst()+", "+token.getSecond()+")  ");
        }
            //  return cycle; // Ciclul hamiltonian a fost găsit
        else {
        System.out.println();
        System.out.println("\u001B[44m \033[1;30m Graful jucatorului nu este hamiltonian. \033[0m\u001B[0m");
          //  return new ArrayList<>(); // Nu s-a găsit un ciclu hamiltonian
        }
    }

    private static void exploreGraph(Graph<Token, DefaultEdge> graph, Token currentNode, List<Token> cycle) {
        Set<Token> neighbors = Graphs.neighborSetOf(graph, currentNode);
        for (Token neighbor : neighbors) {
            if (!cycle.contains(neighbor)) {
                cycle.add(neighbor);
                exploreGraph(graph, neighbor, cycle);
            }
        }
    }
}

