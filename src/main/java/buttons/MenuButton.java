package buttons;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import static java.lang.Math.abs;

/**
 * A base class with the properties that we want all menu buttons to have, such as common width, height, color, and
 * X coordinate.
 */
public class MenuButton extends Button {

    public MenuButton(ScrollPane scrollPane){
        this.setPrefHeight(90);
        this.setPrefWidth(120);
        // this.setStyle("-fx-background-color: #c72c41; ");
        this.setLayoutX(10);

        scrollPane.hvalueProperty().addListener((observable, oldvalue, newvalue) -> {
                    this.setLayoutX(newvalue.doubleValue() + 10);
                }
        );

        scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> this.setLayoutX(abs(newvalue.getMinX()) + 10)
        );
    }
}
