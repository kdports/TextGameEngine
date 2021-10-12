package main.java.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GUIScene extends StackPane {
    int decisionIdControl;
    private double mouseAnchorX;
    private double mouseAnchorY;

    public GUIScene(String s, int idControl, Pane root) {
        decisionIdControl++;
        this.setMaxSize(300, 100);
        this.setStyle("-fx-background-color: Gainsboro;-fx-border-color: blue;");
        this.setId(s + idControl);



        Rectangle rect = new Rectangle(300, 100);

        rect.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        rect.setOnMouseDragged(mouseEvent -> {
            this.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });

        Button b1 = new Button("Create Decision");
        Button b2 = new Button("Delete Scene");
        this.getChildren().addAll(rect, b1, b2);
        StackPane.setAlignment(b1, Pos.TOP_RIGHT);
        StackPane.setAlignment(b2, Pos.BOTTOM_RIGHT);

        b1.setOnMousePressed(mouseEvent -> {
            GUIDecision decision = new GUIDecision(this, root, decisionIdControl);
            root.getChildren().add(decision);
            decision.setLayoutX(this.getLayoutX() + 150);
            decision.setLayoutY(this.getLayoutY() + 50);
        });

        b2.setOnMousePressed(mouseEvent -> {
            root.getChildren().remove(this);
        });


        this.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        this.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                System.out.println("Dropped: " + db.getString());
                StringBuilder desiredDecision = new StringBuilder(db.getString());
                desiredDecision.deleteCharAt(0);
                String outputDecision = desiredDecision.toString();
                if (Character.toString(db.getString().charAt(0)).equals("0")){

                    GUIDecision initialDecision = (GUIDecision) root.lookup("#" + outputDecision);
                    initialDecision.initialScene = this;
                } else {
                    GUIDecision initialDecision = (GUIDecision) root.lookup("#" + outputDecision);
                    initialDecision.secondScene = this;
                }
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });



    }
}
