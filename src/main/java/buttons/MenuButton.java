package buttons;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A base class with the properties that we want all menu buttons to have, such as common width, height, color, and
 * X coordinate.
 */
public class MenuButton extends Button {

    /**
     * Sets the common attributes of every button
     */
    public MenuButton(){
        Rectangle rounded = new Rectangle();
        rounded.setWidth(120);
        rounded.setHeight(90);
        rounded.setArcHeight(30);
        rounded.setArcWidth(30);

        this.setShape(rounded);
        this.setPrefHeight(90);
        this.setPrefWidth(120);
        this.setStyle("-fx-background-color: #99b898; " + "-fx-background-insets: 0;");
        this.setLayoutX(10);
    }
}
