package handlers;

import entities.Decision;
import entities.EditorGame;
import entities.Studio;
//import interfaces.RenderableDecision;
import javafx.scene.input.MouseEvent;

import java.util.Map;

/**
 * A Handler to deal with the actions a user can do to a Decision.
 */
public class DecisionHandler extends BaseHandler {
    public DecisionHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    /**
     * Allow the user to edit the text of the decision.
     *
     * @param decision - The decision to alter.
     * @param message - The new text to set.
     */
    public void editMessage(Decision decision, String message){
        decision.setText(message);
    }

    /**
     * Allow the user to remove a decision.
     *
     * @param decision - The decision to remove.
     */
    public void deleteDecision(Decision decision){
        editorGame.deleteDecision(decision);
    }
}
