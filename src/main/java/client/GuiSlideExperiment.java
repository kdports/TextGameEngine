package client;

import entities.Decision;
import entities.Slide;
import handlers.CreateNewDecisionHandler;
import handlers.Handlers;
import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class GuiSlideExperiment extends StackPane {
    public GuiSlideExperiment(Map.Entry<Slide, RenderableSlide> entry) {
        RenderableSlide renderableSlide = entry.getValue();
        String id = String.valueOf(entry.getKey().getId());
        this.setId(id);
        this.setLayoutX(renderableSlide.getX());
        this.setLayoutY(renderableSlide.getY());
        this.setMinWidth(renderableSlide.getWidth());
        this.setMinHeight(renderableSlide.getHeight());
        this.setStyle("-fx-background-color: lightgray;");

        // Drag event handling
        this.setOnMousePressed(event -> {
            Handlers.slideHandler.beginDrag(entry, event);
        });
        this.setOnMouseDragged(event -> {
             Handlers.slideHandler.drag(entry, event);
             this.setLayoutX(entry.getValue().getX());
             this.setLayoutY(entry.getValue().getY());
         });




        Text prompt = this.addPromptText(entry.getKey().getPrompt());
        Button deleteBtn = this.deleteSlide(entry);
        Button addDecision = this.addDecision(entry);
        Button editBtn = this.editDecision(entry);
        this.getChildren().add(addDecision);
        this.getChildren().add(deleteBtn);
        this.getChildren().add(prompt);
        this.getChildren().add(editBtn);

        entry.getKey().returnObservable().addListener(
                (observable, oldvalue, newvalue) ->
                        prompt.setText(newvalue)
        );

        this.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            Handlers.slideHandler.dropEvent(entry, db.toString());
        });


    }

    private Text addPromptText(String text) {
        Text dialogue = new Text();
        dialogue.setStyle("-fx-blend-mode: overlay");
        dialogue.setText(text);
        dialogue.setFill(Color.BLACK);
        StackPane.setAlignment(dialogue, Pos.CENTER);

        return dialogue;
    }

    private void updateText() {

    }

    // Button to delete Slide

    private Button deleteSlide(Map.Entry<Slide, RenderableSlide> entry) {
        Button deleteButton = new Button("Delete Slide");
        deleteButton.setOnMousePressed(event -> Handlers.slideHandler.delete(entry));
        StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);
        return deleteButton;

    }

    // Button to add Decision

    private Button addDecision(Map.Entry<Slide, RenderableSlide> entry){
        Button addDecisionButton = new Button("Add Decision");
        addDecisionButton.setOnMousePressed(event -> Handlers.createNewDecisionHandler.create(entry));
        StackPane.setAlignment(addDecisionButton, Pos.CENTER_RIGHT);
        return addDecisionButton;

    }

    private Button editDecision(Map.Entry<Slide, RenderableSlide> entry){
        Button addDecisionButton = new Button("Edit Decision");
        addDecisionButton.setOnMousePressed(event -> GuiSlideExperiment.showEdit(entry));
        StackPane.setAlignment(addDecisionButton, Pos.BOTTOM_RIGHT);
        return addDecisionButton;

    }

    private static void showEdit(Map.Entry<Slide, RenderableSlide> entry){
        // Create an Alert Box to edit the Slide

        Stage slideWindow = new Stage();
        slideWindow.initModality(Modality.APPLICATION_MODAL);
        slideWindow.setTitle("Slide Editor");
        slideWindow.setMinWidth(300);
        TextField input = new TextField(entry.getKey().getPrompt());
        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> {
            slideWindow.close();
        });

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Edit the slide message");
        btnEdit.setOnAction(mouseEvent -> {
            Handlers.slideHandler.editMessage(entry, input.getText());
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
