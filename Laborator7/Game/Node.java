package org.example.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

//import javafx.embed.swing.SwingFXUtils;
public class Node extends Circle implements Serializable {

    private boolean clicked = false;

    private DrawingPanel drawingPanel;

    public Node(int x, int y, DrawingPanel drawingPanel) {
        super(x, y, 10);;
        this.drawingPanel = drawingPanel;
        setFill(Color.rgb(0, 0, 0, 0));
        setStroke(Color.BLACK);
        setOnMouseClicked(event -> {
            if(drawingPanel.ok==0){
                if (!clicked) { // Verificăm dacă nodul a fost deja făcut clic
                    drawingPanel.setPreviousNode(this);
                    changeColor();
                    clicked = true;
                    drawingPanel.ok=1;
                }
            } else if (drawingPanel.ok == 1) {
                drawingPanel.setCurrentNode(this);
                if (!drawingPanel.hasConnectedNode()) {
                    closeGame();
                }
                if (!clicked) {
                        changeColor();
                        clicked = true;
                        drawingPanel.setPreviousNode(this);
                }
            }

        });

    }

    public void changeColor() {
        if (drawingPanel.getCurrentPlayer() == 0) {
            setFill(Color.RED); // Dacă este rândul jucătorului 1, setează culoarea roșu
            setStroke(Color.RED);
            drawingPanel.setCurrentPlayer(1);
        } else if (drawingPanel.getCurrentPlayer() == 1) {
            setFill(Color.GREEN); // Dacă este rândul jucătorului 2, setează culoarea verde
            setStroke(Color.GREEN);
            drawingPanel.setCurrentPlayer(0);
        }


    }

    public boolean isClicked() {
        return clicked;
    }

   /* public void saveScreenshot() {
        WritableImage snapshot = this.snapshot(new SnapshotParameters(), null);
        File file = new File("screenshot.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    public void closeGame() {

        //saveScreenshot();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        if(drawingPanel.getCurrentPlayer()==0)
            alert.setContentText("Game Over! The winner is green player.");
        else
            alert.setContentText("Game Over! The winner is red player.");
        // Configurăm alerta să fie modală și să nu permită alte interacțiuni cu aplicația până când este închisă
      //  alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait(); // Afișăm alerta
       // Stage stage = (Stage) getScene().getWindow();
       // stage.close();
    }


    public boolean equal(Node node) {
        if(getCenterX()== node.getCenterX() && getCenterY()==node.getCenterY())
            return true;
        else
            return false;
    }

}


