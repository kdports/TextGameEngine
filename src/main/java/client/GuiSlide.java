package client;

import entities.Slide;
import handlers.Handlers;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GuiSlide extends StackPane {
    private double mouseAnchorX;
    private double mouseAnchorY;
    public Text prompt;

    public GuiSlide(Slide slide, double x_location, double y_location) {
        String id = String.valueOf(slide.getId());
        this.setId(id);
        this.setLayoutX(x_location);
        this.setLayoutY(y_location);
        this.setMaxWidth(200);
        this.setMinWidth(200);
        this.setMaxHeight(100);
        this.setMinHeight(100);
        this.setStyle("-fx-background-color: lightgray;");


        // Drag event handling
        this.setOnMousePressed(event -> {
             mouseAnchorX = event.getX();
             mouseAnchorY = event.getY();
        });
        this.setOnMouseDragged(event -> {
             this.setLayoutX(event.getSceneX() - mouseAnchorX);
             this.setLayoutY(event.getSceneY() - mouseAnchorY);
        });

        prompt = new Text();
        prompt.setText(slide.getPrompt());
        prompt.setStyle("-fx-blend-mode: overlay");
        prompt.setFill(Color.BLACK);
        StackPane.setAlignment(prompt, Pos.CENTER);
        Button addDecision = this.addDecision(slide);
        Button editBtn = this.editSlide(slide);
        Button dltSlide = this.dltSlide(slide);
        this.getChildren().add(addDecision);
        this.getChildren().add(prompt);
        this.getChildren().add(editBtn);
        this.getChildren().add(dltSlide);

        slide.returnObservable().addListener(
                (observable, oldvalue, newvalue) -> prompt.setText(newvalue)
        );

        this.setOnDragOver(new EventHandler<>() {
             public void handle(DragEvent event) {
                 if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                     event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                 }

                 event.consume();
             }
         });

        this.setOnDragDropped((DragEvent event) -> {
             Dragboard db = event.getDragboard();
             Handlers.slideHandler.dropEvent(slide, this, db.getString());
             System.out.println(slide.outgoingDecisions);
        });
    }

    // Button to delete Slide

    private Button dltSlide(Slide slide){
        Button addDecisionButton = new Button("Delete Slide");
        addDecisionButton.setOnMousePressed(
                event -> Handlers.slideHandler.delete(slide));
        StackPane.setAlignment(addDecisionButton, Pos.TOP_RIGHT);
        return addDecisionButton;

    }
    // Button to add Decision

    private Button addDecision(Slide slide){
        Button addDecisionButton = new Button("Add Decision");
        addDecisionButton.setOnMousePressed(
                event -> Handlers.createNewDecisionHandler.create(slide, this, this.getLayoutX(), this.getLayoutY()));
        StackPane.setAlignment(addDecisionButton, Pos.BOTTOM_RIGHT);
        return addDecisionButton;

    }

    private Button editSlide(Slide slide){
        Button addDecisionButton = new Button("Edit Slide");
        addDecisionButton.setOnMousePressed(event -> GuiSlide.showEdit(slide));
        StackPane.setAlignment(addDecisionButton, Pos.TOP_LEFT);
        return addDecisionButton;

    }

    private static void showEdit(Slide slide){
        // Create an Alert Box to edit the Slide

        Stage slideWindow = new Stage();
        slideWindow.initModality(Modality.APPLICATION_MODAL);
        slideWindow.setTitle("Slide Editor");
        slideWindow.setMinWidth(300);
        TextField input = new TextField(slide.getPrompt());
        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> slideWindow.close());

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Edit the slide message");
        btnEdit.setOnAction(mouseEvent -> Handlers.slideHandler.editMessage(slide, input.getText()));

        Button dltButton = new Button("Delete Decision");
        dltButton.setOnAction(mouseEvent -> {
            Handlers.slideHandler.delete(slide);
            slideWindow.close();
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
