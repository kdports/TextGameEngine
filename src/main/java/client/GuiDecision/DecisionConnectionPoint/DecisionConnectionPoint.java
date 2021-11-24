package client.GuiDecision.DecisionConnectionPoint;

import entities.Decision;
import javafx.geometry.Pos;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The abstract class that performs the common responsibilities of a DecisionConnectionPoint, like drag handling
 */
abstract public class DecisionConnectionPoint extends Circle {
    DecisionConnectionPoint(Decision decision) {
        super(5);

        this.setFill(Color.YELLOW);

        Pos position = decidePosition();
        StackPane.setAlignment(this, position);

        String beginningDragCharacter = decideBeginningCharacter();
        this.setOnDragDetected((MouseEvent event) -> {
            Dragboard db = this.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString(beginningDragCharacter + decision.getId());
            db.setContent(content);
        });
    }

    protected abstract String decideBeginningCharacter();
    protected abstract Pos decidePosition();
}
