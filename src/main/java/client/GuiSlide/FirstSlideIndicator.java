package client.GuiSlide;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The visual indicator that tells the user that this slide is marked as first.
 */
public class FirstSlideIndicator extends Circle {
    public FirstSlideIndicator() {
        super();

        this.setFill(Color.RED);
        this.setRadius(5);
        StackPane.setAlignment(this, Pos.BOTTOM_LEFT);
    }
}
