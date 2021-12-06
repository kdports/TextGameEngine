package buttons;

import client.ThemeColours;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

import static java.lang.Math.abs;

/**
 * A base class with the properties that we want all menu buttons to have, such as common width, height, color, and
 * X coordinate.
 */
public class MenuButton extends Button {

    public MenuButton(ScrollPane scrollPane){
        this.setPrefHeight(90);
        this.setPrefWidth(120);
        this.setLayoutX(10);

        scrollPane.hvalueProperty().addListener((observable, oldvalue, newvalue) -> {
                    this.setLayoutX(newvalue.doubleValue() + 10);
                }
        );

        scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> this.setLayoutX(abs(newvalue.getMinX()) + 10)
        );
    }

    public void setTheme(ThemeColours theme){
        this.setStyle("-fx-background-color: " + theme.active.sidebarColour + ";" +
                "-fx-text-fill: " + theme.active.textColour);

        this.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> this.setStyle("-fx-background-color:" + theme.active.textColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.sidebarColour));

        this.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> this.setStyle("-fx-background-color:" + theme.active.sidebarColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.textColour));
    }
}
