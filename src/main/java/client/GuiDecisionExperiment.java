package client;

import entities.Decision;
import entities.Slide;
import handlers.Handlers;
import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
        this.setOnMousePressed(event -> Handlers.dragDecisionHandler.beginDrag(entry, event));
        this.setOnMouseDragged(event -> {
            Handlers.dragDecisionHandler.drag(entry, event);
            this.setLayoutX(entry.getValue().getX());
            this.setLayoutY(entry.getValue().getY());
        });

    }

}
