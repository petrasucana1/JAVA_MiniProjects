package org.example.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;


public class ControlPanel extends FlowPane {
    private final Main frame;
    private Button loadBtn;
    private Button saveBtn;
    private Button exitBtn;

    public ControlPanel(Main frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        loadBtn = new Button("Load");
        saveBtn = new Button("Save");
        exitBtn = new Button("Exit");


        setAlignment(Pos.CENTER);
        setHgap(15);
        setPadding(new Insets(0, 0, 10, 0));

        getChildren().addAll(loadBtn, saveBtn, exitBtn);


        loadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadGame();
            }
        });

        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveGame();
            }
        });

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exitGame();
            }
        });
    }

    private void loadGame() {
        // Logică de încărcare a jocului
    }

    private void saveGame() {
        // Logică de salvare a jocului
    }

    private void exitGame() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }
}
