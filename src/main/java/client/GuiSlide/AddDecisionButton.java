package client.GuiSlide;

import entities.Slide;
import handlers.Handlers;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * A button on the slide that allows the user to create new decisions
 */
public class AddDecisionButton extends Button {
    AddDecisionButton(Slide slide, double parentX, double parentY) {
        this.setText("Add Decision");
        this.setOnMousePressed(
                event -> Handlers.createNewDecisionHandler.create(slide, parentX, parentY)
        );
        StackPane.setAlignment(this, Pos.BOTTOM_RIGHT);
    }

}
