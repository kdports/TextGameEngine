package main.java.client;

import javafx.css.Style;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DecisionAlertBox {


    public static void display(Pane root, GUIDecision decision){
        Stage decisionWindow = new Stage();
        decisionWindow.initModality(Modality.APPLICATION_MODAL);
        decisionWindow.setTitle("Decision Editor");
        decisionWindow.setMinWidth(490);
        decisionWindow.setMaxWidth(490);
        decisionWindow.setMaxHeight(300);
        decisionWindow.setMinHeight(300);

        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(479,  265);
        holder.getChildren().add(canvas);

        Button btnDelete = new Button("Delete Decision");
        btnDelete.setStyle("-fx-background-color: #c72c41; ");
        btnDelete.setOnAction(mouseEvent -> {
            root.getChildren().remove(decision);
            decisionWindow.close();
        });

        TextArea messageInput = new TextArea(decision.message);
        messageInput.setWrapText(true);
        messageInput.setPromptText("Dialogue...");
        messageInput.setStyle("-fx-background-color: #801336;" + "-fx-blend-mode: overlay");

        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> {
            decisionWindow.close();
        });

        Label fromToScene = new Label(decision.initialScene.getId() + " to " + decision.secondScene.getId());

        Label decisionMessage = new Label(decision.message);

        Button btnEdit = new Button("Confirm Dialogue");
        btnEdit.setStyle("-fx-background-color: #c72c41; ");
        btnEdit.setOnAction(mouseEvent -> {
            decision.message = messageInput.getText();
            decisionMessage.setText(decision.message);
            decisionWindow.close();
        });


        Pane alert = new Pane();
        alert.getChildren().addAll(holder, fromToScene, messageInput, btnEdit, btnDelete);
        holder.setStyle("-fx-background-color: #2d142c");
        fromToScene.setStyle("-fx-font-size: 20");
        fromToScene.setLayoutX(155);
        btnEdit.setLayoutX(365);
        btnEdit.setLayoutY(4);
        btnDelete.setLayoutY(4);
        btnDelete.setLayoutX(4);
        messageInput.setLayoutY(60);


        Scene window = new Scene(alert);
        decisionWindow.setScene(window);
        decisionWindow.show();


    }
}
