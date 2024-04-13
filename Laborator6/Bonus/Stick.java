package org.example.game;

public class Stick {
    private Node startNode;
    private Node endNode;

    public Stick(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public boolean containsNode(Node node){
        if(node.equal(startNode) || node.equal(endNode))
            return true;
        else
            return false;
    }

    public Node getOtherNode(Node node){
        if(node.equal(startNode) )
            return endNode;
        else
            return startNode;
    }

    @Override
    public String toString() {
        return "Stick{" +
                "startNode=" + startNode +
                ", endNode=" + endNode +
                '}';
    }
}
