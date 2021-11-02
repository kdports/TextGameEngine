package client;

import entities.Decision;
import entities.Slide;
import handlers.Handlers;
import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class GuiDecisionExperiment extends StackPane {
    public GuiDecisionExperiment(Map.Entry<Decision, RenderableDecision> entry) {
        RenderableDecision renderableDecision = entry.getValue();



        this.setLayoutX(renderableDecision.getX());
        this.setLayoutY(renderableDecision.getY());
        this.setMinWidth(renderableDecision.getWidth());
        this.setMinHeight(renderableDecision.getHeight());
        this.setStyle("-fx-background-color: lightblue;");

        // Drag event handling
        this.setOnMousePressed(event -> Handlers.decisionHandler.beginDrag(entry, event));
        this.setOnMouseDragged(event -> {
            Handlers.decisionHandler.drag(entry, event);
            this.setLayoutX(entry.getValue().getX());
            this.setLayoutY(entry.getValue().getY());
        });

        Button editButton = createEditButton(entry);
        this.getChildren().add(editButton);

        Circle rightConnection = new Circle(5);
        rightConnection.setFill(Color.YELLOW);

        rightConnection.setOnDragDetected((MouseEvent event) -> {

            Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("1," + Handlers.decisionHandler.getId(entry));
            db.setContent(content);
        });
        Circle leftConnection = new Circle(5);

        leftConnection.setFill(Color.YELLOW);

        leftConnection.setOnDragDetected((MouseEvent event) -> {
            Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("0," + Handlers.decisionHandler.getId(entry));
            db.setContent(content);
        });

    }


    public Button createEditButton(Map.Entry<Decision, RenderableDecision> entry){
        Button edtButton = new Button("Edit Decision");
        edtButton.setOnMousePressed(event -> GuiDecisionExperiment.showEdit(entry));
        StackPane.setAlignment(edtButton, Pos.CENTER);
        return edtButton;
    }

    private static void showEdit(Map.Entry<Decision, RenderableDecision> entry){
        // Create an Alert Box to edit the Decision

        Stage slideWindow = new Stage();
        slideWindow.initModality(Modality.APPLICATION_MODAL);
        slideWindow.setTitle("Decision Editor");
        slideWindow.setMinWidth(300);
        TextField input = new TextField(entry.getKey().getText());
        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> {
            slideWindow.close();
        });

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Edit the Decision message");
        btnEdit.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.editMessage(entry, input.getText());
        });



        // Make the Slide Edit Window Show
        VBox alert = new VBox();
        alert.getChildren().addAll(input, btnEdit, btnClose);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        slideWindow.setScene(window);
        slideWindow.show();
    }
}
