package org.example.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Positional game");

        canvas = new DrawingPanel();
        configPanel = new ConfigPanel(canvas, this);
        controlPanel = new ControlPanel(this);



        BorderPane root = new BorderPane();
        root.setTop(configPanel);
        root.setCenter(canvas);
        root.setBottom(controlPanel);




        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void setWindowSize(Stage stage, int lines, int columns) {
        lines++;
        lines *= 50;

        columns++;
        columns *=50;

        double currentWidth = stage.getWidth();
        double currentHeight = stage.getHeight();

        if (lines <= 350 && columns <= 350) {
            stage.setWidth(400);
            stage.setHeight(400);
        }
        else {

            if (lines + 100 > currentHeight && columns > currentWidth) {
                stage.setWidth(columns - 15);
                stage.setHeight(lines + 100);
            } else if (columns > currentWidth) {
                stage.setWidth(columns - 15);
            } else if (lines + 100 > currentHeight) {
                stage.setHeight(lines + 100);
            }

            if (lines + 100 < currentHeight && columns < currentWidth) {
                stage.setWidth(columns - 15);
                stage.setHeight(lines + 100);
            } else if (columns < currentWidth) {
                stage.setWidth(columns - 15);
            } else if (lines + 100 < currentHeight) {
                stage.setHeight(lines + 100);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
