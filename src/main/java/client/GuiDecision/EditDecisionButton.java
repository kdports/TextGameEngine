package client.GuiDecision;

import client.ThemeColours;
import entities.Decision;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * A button on the decision that allows the user to edit the text of the decision.
 */
public class EditDecisionButton extends Button {
    EditDecisionButton(Decision decision, ThemeColours theme) {
        Rectangle rounded = new Rectangle();
        rounded.setWidth(100);
        rounded.setHeight(20);
        rounded.setArcHeight(10);
        rounded.setArcWidth(10);

        this.setText("EDIT");
        this.setShape(rounded);
        this.setMinSize(60, 20);
        this.setMaxSize(60, 20);
        this.setStyle("-fx-background-insets: 0;" +
                "-fx-font-size: 10;"+
                "-fx-background-color: " + theme.active.backgroundColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
        this.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> this.setStyle("-fx-background-insets: 0;" +
                        "-fx-font-size: 10;"+"-fx-background-color:" + theme.active.textColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.backgroundColour));

        this.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> this.setStyle("-fx-background-insets: 0;" +
                        "-fx-font-size: 10;"+"-fx-background-color:" + theme.active.backgroundColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.textColour));
        this.setOnMousePressed(event -> GuiDecision.showEdit(decision));
        StackPane.setAlignment(this, Pos.CENTER);
    }
}
