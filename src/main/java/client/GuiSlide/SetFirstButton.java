package client.GuiSlide;

import client.ThemeColours;
import entities.Slide;
import handlers.Handlers;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * A button to set the first slide of the program
 */
public class SetFirstButton extends Button{
    SetFirstButton(Slide slide, ThemeColours theme) {
        Rectangle rounded = new Rectangle();
        rounded.setWidth(100);
        rounded.setHeight(20);
        rounded.setArcHeight(10);
        rounded.setArcWidth(10);

        this.setText("SET FIRST SLIDE");
        this.setShape(rounded);
        this.setMinSize(100, 20);
        this.setMaxSize(100, 20);
        this.setStyle("-fx-background-insets: 0;" +
                "-fx-font-size: 10;"+
                "-fx-background-color: " + theme.active.backgroundColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
        this.setOnMousePressed(event -> Handlers.slideHandler.setMain(slide));
        StackPane.setAlignment(this, Pos.TOP_CENTER);
    }
}
