package main.java.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class GUIScene extends StackPane {
    public final Button createDecision;
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
        b2.setId("Button2" + idControl);
        b2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                root.getChildren().remove(this);
            }

        });
        this.createDecision = b2;
    }
}
