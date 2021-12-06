package client.GuiSlide;

import client.ThemeColours;
import entities.Slide;
import handlers.Handlers;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.awt.*;
import java.util.Objects;

/**
 * The object representing the slides displayed inside the editor.
 */
public class GuiSlide extends StackPane {
    private double mouseAnchorX;
    private double mouseAnchorY;
    public double sceneX;
    public double sceneY;
    public Text prompt;
    public Rectangle rounded;

    /**
     * Constructs an instance by setting the initial position, colour, and size of
     * the slide
     *
     * @param slide - The slide entity used for data.
     * @param xLocation - The x location of the slide in the editor
     * @param yLocation - The x location of the slide in the editor
     */
    public GuiSlide(Slide slide, double xLocation, double yLocation, ThemeColours theme) {
        String id = String.valueOf(slide.getId());
        this.setId(id);
        this.setLayoutX(xLocation);
        this.setLayoutY(yLocation);
        this.setMaxWidth(200);
        this.setMinWidth(200);
        this.setMaxHeight(100);
        this.setMinHeight(100);

        // Drag event handling
        this.initializeDragHandling();

        TextArea temp_prompt = new TextArea(slide.getPrompt());
        temp_prompt.setMaxHeight(50);
        temp_prompt.paddingProperty().set(new Insets(0,0,-18,0));
        temp_prompt.setWrapText(true);
        if (!Objects.equals(theme.active.slideColour, "#ffffff")) {
            temp_prompt.setStyle("-fx-background-color: TRANSPARENT;" + "-fx-blend-mode: SOFT_LIGHT;");
        }
        temp_prompt.setOnKeyReleased(event -> {
            Handlers.slideHandler.editMessage(slide, temp_prompt.getText());
        });


        Button addDecisionButton = new AddDecisionButton(slide, this, theme);
        addDecisionButton.setId("add-decision-button");
        Button deleteSlideButton = new DeleteSlideButton(slide, theme);
        deleteSlideButton.setId("delete-slide");
        Button setFirstButton = new SetFirstButton(slide, theme);

        // main slide
        rounded = new Rectangle();
        rounded.setWidth(210);
        rounded.setHeight(110);
        rounded.setArcHeight(30);
        rounded.setArcWidth(30);
        rounded.setStroke(Color.BLACK);
        rounded.setFill(Color.valueOf(theme.active.slideColour));
        rounded.setId("GuiRectangle");

        this.getChildren().addAll(rounded, addDecisionButton, deleteSlideButton, setFirstButton, temp_prompt);

        Circle firstSlideIndicator = new FirstSlideIndicator(theme);

        this.initializeListeners(slide, firstSlideIndicator);
    }

    /**
     * Sets the theme of the slde and its elements
     *
     * @param theme - the ThemeColours instance containing the active theme
     */
    public void setTheme(ThemeColours theme){
        rounded.setFill(Color.valueOf(theme.active.slideColour));

        for (Node element : this.getChildren()){
            if (element instanceof AddDecisionButton || element instanceof DeleteSlideButton){
                element.setStyle("-fx-background-insets: 0;" +
                        "-fx-font-size: 10;"+
                        " -fx-background-color: TRANSPARENT;" +
                        "-fx-text-fill: " + theme.active.textColour +";");
            }
            else if (element instanceof SetFirstButton){
                element.setStyle("-fx-background-insets: 0;" +
                        "-fx-font-size: 10;"+
                        "-fx-background-color: " + theme.active.backgroundColour + ";" +
                        "-fx-text-fill: " + theme.active.textColour +";");
            }
            else if (element instanceof TextArea){
                if (!Objects.equals(theme.active.slideColour, "#ffffff")) {
                    element.setStyle("-fx-background-color: TRANSPARENT;" + "-fx-blend-mode: SOFT_LIGHT;");
                }
                else {
                    element.setStyle("-fx-background-color: WHITE;");
                }
            }
        }
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
        });
        this.setOnMouseDragged(event -> {
            this.setLayoutX(sceneX + event.getScreenX() - mouseAnchorX);
            double screenSize = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            if (screenSize >= 1080) {
            this.setLayoutY(sceneY + event.getScreenY() - mouseAnchorY + (1080 - (screenSize)) / 18);}
            else{
                this.setLayoutY(sceneY + event.getScreenY() - mouseAnchorY);
            }
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
        });
    }
}
