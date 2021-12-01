package client.GuiSlide;

import entities.Slide;
import handlers.Handlers;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The object representing the slides displayed inside the editor.
 */
public class GuiSlide extends StackPane {
    private double mouseAnchorX;
    private double mouseAnchorY;
    public double sceneX;
    public double sceneY;
    public Text prompt;

    /**
     * Constructs an instance by setting the initial position, colour, and size of
     * the slide
     *
     * @param slide - The slide entity used for data.
     * @param xLocation - The x location of the slide in the editor
     * @param yLocation - The x location of the slide in the editor
     */
    public GuiSlide(Slide slide, double xLocation, double yLocation) {
        String id = String.valueOf(slide.getId());
        this.setId(id);
        this.setLayoutX(xLocation);
        this.setLayoutY(yLocation);
        this.setMaxWidth(200);
        this.setMinWidth(200);
        this.setMaxHeight(100);
        this.setMinHeight(100);
        this.setStyle("-fx-background-color: TRANSPARENT;");

        // Drag event handling
        this.initializeDragHandling();

        TextArea temp_prompt = new TextArea(slide.getPrompt());
        temp_prompt.setLayoutX(50);
        temp_prompt.setLayoutY(50);
        temp_prompt.setMaxHeight(50);
        temp_prompt.setWrapText(true);
        temp_prompt.setStyle("-fx-background-color: TRANSPARENT;" + "-fx-blend-mode: SOFT_LIGHT");

        Button editButton = new EditSlideButton(slide);
        editButton.setOnMouseClicked(mouseEvent ->
            Handlers.slideHandler.editMessage(slide, temp_prompt.getText())
        );



        Button addDecisionButton = new AddDecisionButton(slide, this);

        Button deleteSlideButton = new DeleteSlideButton(slide);
        Button setFirstButton = new SetFirstButton(slide);

        // main slide
        Rectangle rounded = new Rectangle();
        rounded.setWidth(210);
        rounded.setHeight(110);
        rounded.setArcHeight(30);
        rounded.setArcWidth(30);
        rounded.setStroke(Color.BLACK);
        rounded.setFill(Color.valueOf("#fecea8"));

//        //shadow
//        Rectangle shadow = new Rectangle();
//        shadow.setLayoutX(50);
//        shadow.setWidth(240);
//        shadow.setHeight(140);
//        shadow.setArcHeight(30);
//        shadow.setArcWidth(30);
//        shadow.setFill(Color.BLACK);
//        shadow.opacityProperty().set(0.3);

        this.getChildren().addAll(rounded, addDecisionButton, editButton, deleteSlideButton, setFirstButton, temp_prompt);

        Circle firstSlideIndicator = new FirstSlideIndicator();

        this.initializeListeners(slide, firstSlideIndicator);
    }

    /**
     * Constructor for testing purposes only
     */
    public GuiSlide(String s) {
    }

    /**
     * Create the event handlers to listen to drag and mouse events.
     */
    private void initializeDragHandling() {
        this.setOnMousePressed(event -> {
            mouseAnchorX = event.getX();
            mouseAnchorY = event.getY();
            System.out.println(this.getLayoutY());
        });
        this.setOnMouseDragged(event -> {
            this.setLayoutX(sceneX + event.getScreenX() - mouseAnchorX);
            this.setLayoutY(sceneY + event.getScreenY() - mouseAnchorY);
        });
    }

    /**
     * Set up the listeners for each of the properties that need it.
     * @param slide - The slide that owns which properties need to be listened to.
     */
    private void initializeListeners(Slide slide, Circle firstSlideIndicator) {
        slide.getObservablePrompt().addListener(
                (observable, oldvalue, newvalue) -> prompt.setText(newvalue)
        );

        slide.getObservableFirstSlide().addListener(
                (observable, oldvalue, newvalue) -> {
                    if (newvalue){
                        this.getChildren().add(firstSlideIndicator);
                    } else{
                        this.getChildren().remove(firstSlideIndicator);
                    }
                }
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

    /**
     * Create a dialog box that allows the user to type in new text for their slide.
     * @param slide - The slide on which to change the text.
     */
    static void showEdit(Slide slide){
        Stage slideWindow = new Stage();
        slideWindow.initModality(Modality.APPLICATION_MODAL);
        slideWindow.setTitle("Slide Editor");
        slideWindow.setMinWidth(300);
        TextArea input = new TextArea(slide.getPrompt());
        input.setMinHeight(100);

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Confirm Message Change");
        btnEdit.setOnAction(mouseEvent -> Handlers.slideHandler.editMessage(slide, input.getText()));

        // Make the Slide Edit Window Show
        VBox alert = new VBox();
        alert.getChildren().addAll(input, btnEdit);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        slideWindow.setScene(window);
        slideWindow.show();
    }
}
