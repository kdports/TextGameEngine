package client.GuiSlide;

import entities.Slide;
import handlers.Handlers;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * The button to delete a slide.
 */
public class DeleteSlideButton extends Button {
    DeleteSlideButton(Slide slide) {
        this.setText("Delete Slide");
        this.setOnMousePressed(
                event -> Handlers.slideHandler.delete(slide));
        StackPane.setAlignment(this, Pos.TOP_RIGHT);
    }
}
