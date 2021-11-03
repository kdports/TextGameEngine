package client;

import entities.Decision;
import handlers.Handlers;
import interfaces.RenderableDecision;
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

import java.util.Map;

public class GuiDecisionExperiment extends StackPane {
    private double mouseAnchorX;
    private double mouseAnchorY;
    public Line leftLine;
    public Line rightLine;
    public GuiSlideExperiment originSlide;
    public GuiSlideExperiment targetSlide;

    public GuiDecisionExperiment(Decision decision, GuiSlideExperiment GuiSlide, double x, double y) {


        this.originSlide = GuiSlide;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setMinWidth(100);
        this.setMaxWidth(100);
        this.setMinHeight(50);
        this.setMaxHeight(50);
        this.setStyle("-fx-background-color: lightblue;");

        // Drag event handling
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
        Button editButton = createEditButton(decision);

        Circle rightConnection = new Circle(5);
        rightConnection.setFill(Color.YELLOW);

        rightConnection.setOnDragDetected((MouseEvent event) -> {

            Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("1" + Handlers.decisionHandler.getId(decision));
            db.setContent(content);
        });
        Circle leftConnection = new Circle(5);

        leftConnection.setFill(Color.YELLOW);

        leftConnection.setOnDragDetected((MouseEvent event) -> {
            Dragboard db = rightConnection.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString("0" + Handlers.decisionHandler.getId(decision));
            db.setContent(content);
        });
        this.getChildren().addAll(editButton,rightConnection,leftConnection);
        StackPane.setAlignment(leftConnection, Pos.CENTER_LEFT);
        StackPane.setAlignment(rightConnection, Pos.CENTER_RIGHT);


        // Initializing the Line

        leftLine = new Line(this.getLayoutX(), this.getLayoutY(), this.getLayoutX(), this.getLayoutY() + 25);
        rightLine = new Line(this.getLayoutX()+100, this.getLayoutY()+25, this.getLayoutX()+100, this.getLayoutY()+25);

        leftLine.setVisible(false);
        rightLine.setVisible(false);

        recalculateLeftLineX();
        recalculateLeftLineY();


    }


    public Button createEditButton(Decision decision){
        Button edtButton = new Button("Edit");
        edtButton.maxHeight(25);
        edtButton.maxWidth(50);
        edtButton.setOnMousePressed(event -> GuiDecisionExperiment.showEdit(decision));
        StackPane.setAlignment(edtButton, Pos.CENTER);
        return edtButton;
    }

    public void recalculateLeftLineX(){
        leftLine.setVisible(originSlide != null);
        leftLine.setStartX(originSlide.getLayoutX() + 200);
    }

    public void recalculateLeftLineY(){
        leftLine.setStartY(originSlide.getLayoutY() + 50);
    }

    public void recalculateRightLineX(){
        rightLine.setVisible(targetSlide != null);
        rightLine.setEndX(targetSlide.getLayoutX());
    }

    public void recalculateRightLineY(){
        rightLine.setEndY(targetSlide.getLayoutY() + 50);
    }


    private static void showEdit(Decision decision){
        // Create an Alert Box to edit the Decision

        Stage slideWindow = new Stage();
        slideWindow.initModality(Modality.APPLICATION_MODAL);
        slideWindow.setTitle("Decision Editor");
        slideWindow.setMinWidth(300);
        TextField input = new TextField(decision.getText());
        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> {
            slideWindow.close();
        });

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Edit the Decision message");
        btnEdit.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.editMessage(decision, input.getText());
        });

        Button dltButton = new Button("Delete Decision");
        dltButton.setOnAction(mouseEvent -> {
            Handlers.decisionHandler.deleteDecision(decision);
            slideWindow.close();
        });
        System.out.println(decision.getOrigin().getId());



        // Make the Slide Edit Window Show
        VBox alert = new VBox();
        alert.getChildren().addAll(input, btnEdit, btnClose, dltButton);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        slideWindow.setScene(window);
        slideWindow.show();
    }
}
