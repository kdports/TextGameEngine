package handlers;

import entities.Decision;
import entities.EditorGame;
import entities.Studio;


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

    public void changeDecisionConditional(Decision decision, Decision target) {
        decision.switchDecisionConditional(target);

    }

    /**
     * Calls decision's switchItemConditional function to either add or remove an item from a conditional
     * @param decision The decision's item conditional to switch
     * @param item The item to switch
     */
    public void changeItemConditional(Decision decision, String item) { decision.switchItemConditional(item); }

    /**
     * Changes what item the decision gives when selected
     * @param decision The decision that will give an item
     * @param item The item that will be given
     */
    public void changeGivenItem(Decision decision, String item) {
        decision.setItemToGive(item);
    }

    /**
     * Returns true if toGet is in decision's list of conditionals
     * @param decision Decision to check conditionals of
     * @param toGet Decision to check for presence of
     * @return true if toGet is in decision's conditionals
     */
    public boolean hasDecisionConditional(Decision decision, Decision toGet) {
        return decision.getDecisionConditionals().contains(toGet);
    }
}
