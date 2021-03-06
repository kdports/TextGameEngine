package client.GuiSlide;

import client.ThemeColours;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The visual indicator that tells the user that this slide is marked as first.
 */
public class FirstSlideIndicator extends Circle {
    public FirstSlideIndicator(ThemeColours theme) {
        super();

        this.setStroke(Color.valueOf(theme.active.backgroundColour));
        this.setRadius(7);
        StackPane.setAlignment(this, Pos.BOTTOM_CENTER);
    }
}
