package client;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GUIDecision extends StackPane {
    public String message = "Insert Dialogue...";
    public GUIScene initialScene;
    public GUIScene secondScene;
    private double mouseAnchorX;
    private double mouseAnchorY;

    //TODO: Create aesthetically pleasing decision rod
    public GUIDecision(GUIScene firstScene, Pane root, int decisionIdControl){
        this.initialScene = firstScene;
        this.secondScene = firstScene;
        this.setMaxSize(300, 5);
        this.setMinSize(300, 5);
        this.setStyle("-fx-background-color: #ee4540");

        Button EditBtn = new Button("Edit Decision");
        EditBtn.setStyle("-fx-background-color: #c72c41");
        EditBtn.setOnMousePressed(mouseEvent -> {
            DecisionAlertBox.display(root, this);
        });

        this.setId(firstScene.getId() + "," + decisionIdControl);

        Circle centerCircle = new Circle(5);
        centerCircle.setFill(Color.GRAY);
        centerCircle.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        centerCircle.setOnMouseDragged(mouseEvent -> {
            this.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            this.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });

        Circle rightConnection = new Circle(5);
        rightConnection.setFill(Color.YELLOW);

        rightConnection.setOnDragDetected((MouseEvent event) -> {

            Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("1" + firstScene.getId() + "," + decisionIdControl);
            db.setContent(content);
        });


        Circle leftConnection = new Circle(5);
        leftConnection.setFill(Color.YELLOW);

        leftConnection.setOnDragDetected((MouseEvent event) -> {

            Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("0" + firstScene.getId() + "," + decisionIdControl);
            db.setContent(content);
        });

        this.getChildren().addAll(leftConnection, EditBtn, rightConnection, centerCircle);
        StackPane.setAlignment(leftConnection, Pos.BASELINE_LEFT);
        StackPane.setAlignment(EditBtn, Pos.CENTER);
        StackPane.setAlignment(rightConnection, Pos.TOP_RIGHT);
        StackPane.setAlignment(centerCircle, Pos.BOTTOM_CENTER);



    }
}
