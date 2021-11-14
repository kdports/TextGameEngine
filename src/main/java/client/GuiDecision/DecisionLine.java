package client.GuiDecision;

import client.GuiSlide.GuiSlide;
import javafx.beans.binding.Binding;
import javafx.beans.property.Property;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Line;

/**
 * The line that connects the decision to either the ORIGIN slide or the TARGET slide
 */
public class DecisionLine extends Line {
    private final ConnectionDirection parameter;
    public GuiSlide decidedSlide;

    DecisionLine(double x1, double y1, double x2, double y2, ConnectionDirection parameter, GuiSlide decidedSlide) {
        // Change the location based on the parameter, being ORIGIN or TARGET
        super(x1, y1, x2, y2);

        this.setVisible(false);
        this.decidedSlide = decidedSlide;
        this.parameter = parameter;

        this.recalculateX();
        this.recalculateY();
    }

    /**
     * Reposition this line's X coordinate based on some movement, i.e. dragging.
     */
    public void recalculateX() {
        this.setVisible(decidedSlide != null);
        if (decidedSlide != null) {
            if (parameter == ConnectionDirection.ORIGIN) {
                // Change this from hardcoded width of box to actually the box's width, so that it changes.
                this.setStartX(decidedSlide.getLayoutX() + 200);
            } else if (parameter == ConnectionDirection.TARGET) {
                this.setEndX(decidedSlide.getLayoutX());
            }
        }
    }

    /**
     * Set the decidedSlide if it ever changes.
     *
     * @param slide - The slide to set it to.
     */
    public void setSlide(GuiSlide slide) {
        this.decidedSlide = slide;
    }

    /**
     * Reposition this line's Y coordinate based on some movement, i.e. dragging.
     */
    public void recalculateY() {
        this.setVisible(decidedSlide != null);
        if (this.decidedSlide != null) {
            if (parameter == ConnectionDirection.ORIGIN) {
                // Change this from hardcoded height of box to actually the box's height, so that it changes.
                this.setStartY(this.decidedSlide.getLayoutY() + 50);
            } else if (parameter == ConnectionDirection.TARGET) {
                this.setEndY(this.decidedSlide.getLayoutY() + 50);
            }
        }
    }
}
