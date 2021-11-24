package handlers;

import client.GuiSlide.GuiSlide;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;

/**
 * A handler that deals with the various actions the user is allowed to do to a pre-existing slide. Note: does NOT
 * handle the creation of new slides.
 */
public class SlideHandler extends BaseHandler {
    public SlideHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    /**
     * Allows the user to delete slides.
     *
     * @param slide - The slide to delete.
     */
    public void delete(Slide slide){
        editorGame.deleteSlide(slide);
    }

    /**
     * Allows the user to change the text on a slide.
     *
     * @param slide - The slide to change the text of.
     * @param message - The new text to set.
     */
    public void editMessage(Slide slide, String message){
        slide.setPrompt(message);
    }

    /**
     * Allows the user to set a slide as the first slide.
     *
     * @param slide - The slide to set as first.
     */
    public void setMain(Slide slide){
        editorGame.setFirstSlide(slide);
    }

    /**
     * Allows the user to draw a decision to connect to this decision.
     *
     * @param slide - The slide that is being connected to
     * @param GuiSlide - The GUI version of the slide being connected to.
     * @param db - A string that was copied to the user's clipboard to determine which side (origin/target) of the slide
     *           to connect to.
     */
    public void dropEvent(Slide slide, GuiSlide GuiSlide, String db) {
         StringBuilder desiredDecision = new StringBuilder(db);
         desiredDecision.deleteCharAt(0);

         if (Character.toString(db.charAt(0)).equals("0")) {
             editorGame.changeDecisionOrigin(slide, GuiSlide, Integer.parseInt(desiredDecision.toString()));
         }

         if (Character.toString(db.charAt(0)).equals("1")) {
             editorGame.changeDecisionTarget(slide, GuiSlide, Integer.parseInt(desiredDecision.toString()));
         }
     }
}
