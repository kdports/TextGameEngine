package client.GuiDecision;

import client.GuiDecision.DecisionConnectionPoint.LeftDecisionConnectionPoint;
import client.GuiDecision.DecisionConnectionPoint.RightDecisionConnectionPoint;
import client.GuiSlide.GuiSlide;
import entities.Decision;
import handlers.Handlers;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GuiDecision extends StackPane {
    private double mouseAnchorX;
    private double mouseAnchorY;
    public DecisionLine leftLine;
    public DecisionLine rightLine;
    public GuiSlide originSlide;
    public GuiSlide targetSlide;

    public GuiDecision(Decision decision, GuiSlide guiSlide, double x, double y) {
        this.originSlide = guiSlide;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setMinWidth(100);
        this.setMaxWidth(100);
        this.setMinHeight(50);
        this.setMaxHeight(50);
        this.setStyle("-fx-background-color: lightblue;");

        // Drag event handling
        this.initializeDragHandling();

        Button editButton = new EditDecisionButton(decision);
        Circle leftConnection = new LeftDecisionConnectionPoint(decision);
        Circle rightConnection = new RightDecisionConnectionPoint(decision);

        this.getChildren().addAll(editButton,rightConnection,leftConnection);

        // Initializing the two DecisionLine's
        leftLine = new DecisionLine(
                this.getLayoutX(),
                this.getLayoutY(),
                this.getLayoutX(),
                this.getLayoutY() + 25,
                ConnectionDirection.ORIGIN,
                originSlide
        );
        rightLine = new DecisionLine(
                this.getLayoutX() + 100,
                this.getLayoutY() + 25,
                this.getLayoutX() + 100,
                this.getLayoutY() + 25,
                ConnectionDirection.TARGET,
                targetSlide
        );

        // targetSlide.addListener(event -> {
        //     rightLine.setSlide(this.targetSlide.getValue());
        // });
    }

    public GuiDecision(String s) {
        // This is for testing/debug purposes only
        // Do not use outside of Test classes!

    }

    /**
     * Create the event handlers to deal with drag events.
     */
    private void initializeDragHandling() {
        this.setOnMousePressed(event -> {
            mouseAnchorX = event.getX();
            mouseAnchorY = event.getY();

        });
        this.setOnMouseDragged(event -> {
            this.setLayoutX(event.getSceneX() - mouseAnchorX);
            this.setLayoutY(event.getSceneY() - mouseAnchorY);
            leftLine.setEndX(event.getSceneX() - mouseAnchorX);
            leftLine.setEndY(event.getSceneY() - mouseAnchorY + 25);
            rightLine.setStartX(event.getSceneX() - mouseAnchorX + 100);
            rightLine.setStartY(event.getSceneY() - mouseAnchorY + 25);
        });
    }

    /**
     * Show the dialog box that allows the user to change the text on a decision.
     *
     * @param decision - The decision to change the text of.
     */
    static void showEdit(Decision decision) {
        // Create an Alert Box to edit the Decision
        Stage slideWindow = new Stage();
        slideWindow.initModality(Modality.APPLICATION_MODAL);
        slideWindow.setTitle("Decision Editor");
        slideWindow.setMinWidth(300);
        TextField input = new TextField(decision.getText());
        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> slideWindow.close());

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Edit the Decision message");
        btnEdit.setOnAction(mouseEvent -> Handlers.decisionHandler.editMessage(decision, input.getText()));

        Button dltButton = new Button("Delete Decision");
        dltButton.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.deleteDecision(decision);
            slideWindow.close();
        });

        // Make the Slide Edit Window Show
        VBox alert = new VBox();
        alert.getChildren().addAll(input, btnEdit, btnClose, dltButton);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        slideWindow.setScene(window);
        slideWindow.show();
    }
}
