package org.example;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;

public class ProblemGraph {
    Problem problem;
    Graph<Person, DefaultEdge> graph;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Graph<Person, DefaultEdge> getGraph() {
        return graph;
    }

    public void setGraph(Graph<Person, DefaultEdge> graph) {
        this.graph = graph;
    }

    public ProblemGraph(Problem problem) {
        this.problem = problem;
    }

    public void createGraph() {
        graph = new DirectedMultigraph<>(DefaultEdge.class);

        //add vertexes
        for(Driver driver: problem.getDrivers())
            graph.addVertex(driver);

        for(Passenger passenger: problem.getPassengers())
            graph.addVertex(passenger);

        //add edges
        for (Passenger passenger : problem.getPassengers()) {
            for (Driver driver : problem.getDrivers()) {
                if ((driver.getDestination().equals(passenger.getDestination()) ) || (driver.getRoute() == passenger.getRoute())) {
                        graph.addEdge(driver,passenger);
                }
            }
        }

    }

    public void printGraph(){
        System.out.println("Nodes:");
        for (Person vertex : graph.vertexSet()) {
            System.out.println(vertex.getName());
        }

        System.out.println("Edges:");
        for (DefaultEdge edge : graph.edgeSet()) {
            System.out.println(graph.getEdgeSource(edge).getName() + " -> " + graph.getEdgeTarget(edge).getName());
        }
    }



}
