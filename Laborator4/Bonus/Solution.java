package org.example;
import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
public class Solution {
    private Problem problem;
    private ProblemGraph problemGraph;

    public Solution(Problem problem, ProblemGraph graph) {
        this.problem = problem;
        this.problemGraph = graph;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public ProblemGraph getGraph() {
        return problemGraph;
    }

    public void setGraph(ProblemGraph graph) {
        this.problemGraph = graph;
    }

    public List<Passenger> findMatchings() {
        List<Passenger> unmatchedPassengers = new ArrayList<>();
        for (Passenger passenger : problem.getPassengers()) {
            int match = 0;

            for (Driver driver : problem.getDrivers()) {
                if (problemGraph.graph.containsEdge(passenger, driver) || problemGraph.graph.containsEdge(driver, passenger))
                    if (driver.getStatus()) {
                        driver.setPassenger(passenger);
                        driver.setStatus(false);
                        match = 1;
                        break;
                    }
            }

            if (match == 0)
                    unmatchedPassengers.add(passenger);
        }

        return  unmatchedPassengers;
    }


}
