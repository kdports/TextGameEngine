package client.GuiSlide;

import client.ThemeColours;
import entities.Slide;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * A button to allow the user to change the text on a slide.
 */
public class EditSlideButton extends Button{
    EditSlideButton(Slide slide, ThemeColours theme) {
        Rectangle rounded = new Rectangle();
        rounded.setWidth(100);
        rounded.setHeight(20);
        rounded.setArcHeight(10);
        rounded.setArcWidth(10);

        this.setText("SAVE TEXT");
        this.setShape(rounded);
        this.setMinSize(100, 20);
        this.setMaxSize(100, 20);
        this.setStyle("-fx-background-insets: 0;" +
                "-fx-font-size: 10;"+
                "-fx-background-color: " + theme.active.backgroundColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
        // this.setOnMousePressed(event -> GuiSlide.showEdit(slide));
        StackPane.setAlignment(this, Pos.TOP_CENTER);
    }
}
