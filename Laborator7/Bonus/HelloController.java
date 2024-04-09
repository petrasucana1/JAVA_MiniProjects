package org.example.game;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public class HelloController {
    private DrawingPanel panel;

    public HelloController(DrawingPanel panel) {
        this.panel = panel;
    }

    public void setEvents(){
        for(Node node: panel.getGridNodes()){
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    node.setRadius(7); // actualizare raza
                    node.setFill(Color.RED);
                }
            });
            panel.getChildren().add(node);
        }
    }
}

/*void playGame() {
    for (Node node : sticksNodes) {
        EventHandler<MouseEvent> clickOnNodes = event -> {
            if (!isStickOccupied(node)) {
                stones.add(new Stone(node.getX(), node.getY(), turn == 0 ? "green" : "red"));
                drawStone(node.getX(), node.getY(), turn);
                turn = (turn + 1) % 2; // Schimbă rândul jucătorului
            }
        };

        node.setOnMouseClicked(clickOnNodes);
        getChildren().add(node);
    }

}

boolean isStickOccupied(Node node) {
    for (Stone stone : stones) {
        if (stone.getX() == node.getX() && stone.getY() == node.getY()) {
            return true;
        }
    }
    return false;
}



void drawStone(int x, int y, int turn){
    Circle circle = new Circle(x, y, 8);
    if(turn==0)
        circle.setFill(GREEN);
    else
        circle.setFill(RED);
    getChildren().add(circle);
}*/