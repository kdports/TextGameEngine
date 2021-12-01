package client.GuiSlide;

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

        prompt = new Text();
        prompt.setWrappingWidth(150);
        prompt.setText(slide.getPrompt());
//        prompt.setStyle("-fx-blend-mode: overlay");
        prompt.setFill(Color.BLACK);
        StackPane.setAlignment(prompt, Pos.CENTER);

        Button addDecisionButton = new AddDecisionButton(slide, this);
        Button editButton = new EditSlideButton(slide);
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

        this.getChildren().addAll(rounded, addDecisionButton, editButton, deleteSlideButton, setFirstButton, prompt);

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
        TextField input = new TextField(slide.getPrompt());
        Button btnClose = new Button("Close this window");
        btnClose.setOnAction(mouseEvent -> slideWindow.close());

        // Edit the value on entry according to the value on the text field
        Button btnEdit = new Button("Edit the slide message");
        btnEdit.setOnAction(mouseEvent -> Handlers.slideHandler.editMessage(slide, input.getText()));

        // Make the Slide Edit Window Show
        VBox alert = new VBox();
        alert.getChildren().addAll(input, btnEdit, btnClose);
        alert.setAlignment(Pos.CENTER);

        Scene window = new Scene(alert);
        slideWindow.setScene(window);
        slideWindow.show();
    }
}
