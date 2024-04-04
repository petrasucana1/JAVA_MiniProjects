package org.example.game;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;


public class DrawingPanel extends StackPane {

    int gridLines = 5;
    int gridColumns = 5;

    public DrawingPanel() {
        setPrefSize(300, 300); // Setăm o dimensiune preferată pentru panou
        setAlignment(Pos.CENTER); // Aliniem panoul la centru
        layoutChildren(); // Inițializăm desenarea grilei
    }
    public void setGridSize(int lines, int columns) {
        this.gridLines  = lines;
        this.gridColumns=columns;
        layoutChildren(); // Re-desenează grila când se schimbă dimensiunea
    }

    @Override
    protected void layoutChildren() {
        getChildren().clear(); // Curăță vechile linii înainte de redesenare

        // Desenează grila
        int width = gridColumns * 50;
        int height = gridLines * 50;

        // Desenează liniile verticale
        for (int x = 10; x <= width + 10; x += 50) {
            Line line = new Line(x, 10, x, height + 10);
            getChildren().add(line);
        }

        // Desenează liniile orizontale
        for (int y = 10; y <= height + 10; y += 50) {
            Line line = new Line(10, y, width + 10, y);
            getChildren().add(line);
        }
    }
}
