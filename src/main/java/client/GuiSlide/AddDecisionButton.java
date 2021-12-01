package client.GuiSlide;

import entities.Slide;
import handlers.Handlers;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * A button on the slide that allows the user to create new decisions
 */
public class AddDecisionButton extends Button {
    AddDecisionButton(Slide slide, GuiSlide guiSlide) {
        this.setText("+");
        this.setShape(new Circle(5.0));
        this.setMinSize(20.0, 20.0);
        this.setMaxSize(20.0, 20.0);
        this.setStyle("-fx-background-insets: 0;" +
                "-fx-font-size: 10;"+
                " -fx-background-color: TRANSPARENT");
        this.setOnMousePressed(
                event -> Handlers.createNewDecisionHandler.create(slide, guiSlide.getLayoutX(), guiSlide.getLayoutY())
        );
        StackPane.setAlignment(this, Pos.TOP_RIGHT);
    }
}
