package org.example.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ConfigPanel extends FlowPane {
    Label label;

    Button newGameButton;
    Spinner<Integer> spinner;

    public ConfigPanel(DrawingPanel drawingPanel, Main main) {
        label = new Label("Grid Size:");
        Spinner<Integer> spinner1 = new Spinner<>();
        spinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 5, 1));// Min, Max, Initial, Step

        Spinner<Integer> spinner2 = new Spinner<>();
        spinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 5, 1));

        newGameButton = new Button("New Game");


        spinner1.setPrefWidth(100); // Lățimea preferată pentru primul spinner
        spinner2.setPrefWidth(100);
        setPadding(new Insets(5));
        setHgap(10);
        setAlignment(Pos.CENTER);

        getChildren().addAll(label, spinner1, spinner2, newGameButton);

        // Adăugați un gestionar de evenimente pentru butonul "New Game"
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int gridLines = spinner1.getValue();
                int gridColumns= spinner2.getValue();
                drawingPanel.setGridSize(gridLines,gridColumns);
                Stage stage = (Stage) getScene().getWindow();
                main.setWindowSize(stage,gridLines,gridColumns);
            }
        });
    }
}
