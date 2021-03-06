package client.GuiSlide;

import client.ThemeColours;
import entities.Slide;
import handlers.Handlers;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * The button to delete a slide.
 */
public class DeleteSlideButton extends Button {
    DeleteSlideButton(Slide slide, ThemeColours theme) {
        this.setText("x");
        this.setShape(new Circle(5.0));
        this.setMinSize(30.0, 30.0);
        this.setMaxSize(30.0, 30.0);
        this.setStyle("-fx-background-insets: 0;" +
                "-fx-font-size: 10;"+
                " -fx-background-color: TRANSPARENT;" +
                "-fx-text-fill: " + theme.active.textColour +";");
        this.setOnMousePressed(
                event -> Handlers.slideHandler.delete(slide));
        StackPane.setAlignment(this, Pos.TOP_LEFT);
    }
}
