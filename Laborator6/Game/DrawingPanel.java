package org.example.game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.*;

import static javafx.scene.paint.Color.*;


public class DrawingPanel extends StackPane implements Serializable {

    private static final long serialVersionUID = 1L;

    private int gridLines = 5;
    private int gridColumns = 5;
    private int currentPlayer=0;
    private Node currentNode;
    private Node previousNode;
    public int ok=0;

   // private List<Line> lines=new ArrayList<>();
    private List<Node> gridNodes= new ArrayList<>();
    private Set<Stick> sticks = new HashSet<>();
    private Set<Node> sticksNodes= new HashSet<>();
   // private List<Stone> stones= new ArrayList<>();

    public List<Node> getGridNodes() {
        return gridNodes;
    }

    public Set<Stick> getSticks() {
        return sticks;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public DrawingPanel() {
        setPrefSize(300, 300);
        setGridSize(5,5);
      //  layoutChildren();
    }
    public void setGridSize(int lines, int columns) {
        this.gridLines  = lines;
        this.gridColumns=columns;
        ok=0;
        setCurrentPlayer(0);
        //layoutChildren();

    }
    public void setCurrentPlayer(int player) {
        this.currentPlayer = player;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }


    @Override
    protected void layoutChildren() {
        Random random = new Random();

        getChildren().clear(); // Curăță vechile linii înainte de redesenare
        //lines.clear();
        //gridNodes.clear();
        sticks.clear();
        sticksNodes.clear();
        // stones.clear();

        // Desenează grila
        int width = gridColumns * 50;
        int height = gridLines * 50;

        // Desenează liniile verticale
        for (int x = 10; x <= width + 10; x += 50) {
            Line line = new Line(x, 10, x, height + 10);
          //  lines.add(line);
            getChildren().add(line);
        }

        // Desenează liniile orizontale
        for (int y = 10; y <= height + 10; y += 50) {
            Line line = new Line(10, y, width + 10, y);
           // lines.add(line);
            getChildren().add(line);
        }

        generateSticks(height, width);
        generateNodes(height, width);
    }
    void generateSticks(int width,int height){
        Random random = new Random();

        for (int y = 10; y <= height + 10; y += 50){
            for (int x = 10; x <= width -40; x += 50){
                Line stick = new Line(y,x,y,x+50);
                boolean RandomStick=random.nextBoolean();
                if(RandomStick){
                    stick.setStrokeWidth(4);
                    Node startNode = getNodeIfExists(y, x);
                    Node endNode = getNodeIfExists(y, x + 50);
                    if (startNode != null && endNode != null) {
                        sticks.add(new Stick(startNode, endNode));
                        sticksNodes.add(startNode);
                        sticksNodes.add(endNode);
                        getChildren().add(stick);
                    }
                }
            }
        }

        for (int x = 10; x <= width -40; x += 50){
            for (int y = 10; y <= height -40; y += 50){
                Line stick = new Line(y,x,y+50,x);
                boolean RandomStick=random.nextBoolean();
                if(RandomStick) {
                    stick.setStrokeWidth(4);
                    Node startNode = getNodeIfExists(y, x);
                    Node endNode = getNodeIfExists(y + 50, x);
                    if (startNode != null && endNode != null) {
                        sticks.add(new Stick(startNode, endNode));
                        sticksNodes.add(startNode);
                        sticksNodes.add(endNode);
                        getChildren().add(stick);
                    }
                }
            }

    }

}
    private Node getNodeIfExists(double x, double y) {
        for (Node node : gridNodes) {
            if (node.getCenterX() == x && node.getCenterY() == y) {
                return node;
            }
        }
        return null;
    }

    void generateNodes(int width,int height) {
        for (int y = 10; y <= height + 10; y += 50) {
            for (int x = 10; x <= width + 10; x += 50) {
                Node node = new Node(y, x, this);
                gridNodes.add(node);
                getChildren().add(node);

            }
        }
    }

    public boolean hasConnectedNode() {
        int ok=0;
        for (Stick stick : sticks) {
            if (stick.containsNode(previousNode)) {
               if(stick.getOtherNode(previousNode).equal(currentNode))
                    if (!currentNode.isClicked()) {
                        return true;
                    }
            }
        }
         return false;

    }
    }



