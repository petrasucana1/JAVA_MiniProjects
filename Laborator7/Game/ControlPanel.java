package org.example.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.io.*;


public class ControlPanel extends FlowPane {
    private final Main frame;
    private DrawingPanel panel;
    private Button loadBtn;
    private Button saveBtn;
    private Button exitBtn;

    public ControlPanel(Main frame,DrawingPanel panel) {
        this.panel=panel;
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("LoadGame");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                DrawingPanel panel2= (DrawingPanel) ois.readObject();

                panel.getChildren().clear();
                for (javafx.scene.Node node : panel2.getChildren())
                    panel.getChildren().add(node);

                panel.setCurrentPlayer(panel2.getCurrentPlayer());

                panel.ok=panel2.ok;

                panel.setCurrentNode(panel2.getCurrentNode());

                panel.setPreviousNode(panel2.getPreviousNode());

                System.out.println("Game loaded successfully!");
            } catch (IOException  | ClassNotFoundException e) {
                System.out.println("Failed to load the game!");
                e.printStackTrace();
            }
        }
    }


   private void saveGame() {
       FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("SaveGame");
       File file = fileChooser.showSaveDialog(null);

       if (file != null) {
           try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
               oos.writeObject(panel);

               System.out.println("Game saved successfully!");
           } catch (IOException e) {
               System.out.println("Failed to save the game!");
               e.printStackTrace();
           }
       }
   }



    private void exitGame() {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();

    }

}
