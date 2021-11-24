package client.GuiSlide;

import entities.Slide;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * A button to allow the user to change the text on a slide.
 */
public class EditSlideButton extends Button{
    EditSlideButton(Slide slide) {
        this.setText("Edit Slide");
        this.setOnMousePressed(event -> GuiSlide.showEdit(slide));
        StackPane.setAlignment(this, Pos.TOP_LEFT);
    }
}
