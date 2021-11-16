package buttons;

import javafx.scene.control.Button;

/**
 * A base class with the properties that we want all menu buttons to have, such as common width, height, color, and
 * X coordinate.
 */
public class MenuButton extends Button {

    /**
     * Sets the common attributes of every button
     */
    public MenuButton(){
        this.setPrefHeight(90);
        this.setPrefWidth(120);
        this.setStyle("-fx-background-color: #c72c41; ");
        this.setLayoutX(10);
    }
}
