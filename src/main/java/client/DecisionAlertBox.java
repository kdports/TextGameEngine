package main.java.client;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DecisionAlertBox {


    public static void display(Pane root, GUIDecision decision){
        Stage decisionWindow = new Stage();
        decisionWindow.initModality(Modality.APPLICATION_MODAL);
        decisionWindow.setTitle("Decision Editor");
        decisionWindow.setMinWidth(300);

        Button btnDelete = new Button("Delete this decision");
        btnDelete.setOnAction(mouseEvent -> {
            root.getChildren().remove(decision);
            decisionWindow.close();
        });

        TextField messageInput = new TextField(decision.message);

        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> {
            decisionWindow.close();
        });

        Label fromToScene = new Label(decision.initialScene.getId() + " to " + decision.secondScene.getId());

        Label decisionMessage = new Label(decision.message);

        Button btnEdit = new Button("Edit the decision message");
        btnEdit.setOnAction(mouseEvent -> {
            decision.message = messageInput.getText();
            decisionMessage.setText(decision.message);

        });


        VBox alert = new VBox();
        alert.getChildren().addAll(fromToScene, decisionMessage, messageInput, btnEdit, btnDelete);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        decisionWindow.setScene(window);
        decisionWindow.show();


    }
}
