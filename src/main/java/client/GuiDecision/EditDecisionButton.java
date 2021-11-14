package client.GuiDecision;

import entities.Decision;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * A button on the decision that allows the user to edit the text of the decision.
 */
public class EditDecisionButton extends Button {
    EditDecisionButton(Decision decision) {
        this.setText("Edit");
        this.maxHeight(25);
        this.maxWidth(50);
        this.setOnMousePressed(event -> GuiDecision.showEdit(decision));
        StackPane.setAlignment(this, Pos.CENTER);
    }
}
