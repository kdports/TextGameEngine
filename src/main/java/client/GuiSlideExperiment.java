package client;

import entities.EditorGame;
import entities.Slide;
import handlers.Handlers;
import interfaces.RenderableSlide;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.util.Map;

public class GuiSlideExperiment extends VBox {

    private double mouseAnchorX;
    private double mouseAnchorY;

    public GuiSlideExperiment(Map.Entry<Slide, RenderableSlide> entry) {
        RenderableSlide renderableSlide = entry.getValue();

        this.setLayoutX(renderableSlide.getX());
        this.setLayoutY(renderableSlide.getY());
        this.setMinWidth(renderableSlide.getWidth());
        this.setMinHeight(renderableSlide.getHeight());
        this.setStyle("-fx-background-color: lightgray;");

        // Drag event handling
        this.setOnMousePressed(event -> Handlers.dragSlideHandler.beginDrag(entry, event));
        this.setOnMouseDragged(event -> {
             Handlers.dragSlideHandler.drag(entry, event);
             this.setLayoutX(entry.getValue().getX());
             this.setLayoutY(entry.getValue().getY());
         });




        Text prompt = this.addPromptText(entry.getKey().getPrompt());
        this.getChildren().add(prompt);
    }

    private Text addPromptText(String text) {
        Text dialogue = new Text();
        dialogue.setStyle("-fx-blend-mode: overlay");
        dialogue.setText(text);
        dialogue.setFill(Color.BLACK);
        StackPane.setAlignment(dialogue, Pos.CENTER);

        return dialogue;
    }
}
