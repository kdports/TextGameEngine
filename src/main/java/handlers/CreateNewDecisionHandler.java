package handlers;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import client.ThemeColours;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import entities.Decision;
import utils.IdControl;

/**
 * The handler that allows the user to create new decisions on a slide.
 */
public class CreateNewDecisionHandler extends BaseHandler {
    public CreateNewDecisionHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    /**
     * Allows the user to create a new decision hanging off a slide.
     *
     * @param slide - The slide that will be the origin of the decision.
     * @param x - The X coordinate of where to create the decision.
     * @param y - The Y coordinate of where to create the decision.
     */
    public void create(Slide slide, double x, double y, ThemeColours theme){
        IdControl.addOne();
        GuiSlide guiSlide = this.editorGame.getSlideMap().get(slide);

        // Create the decision
        Decision decision = new Decision("I am a new decision", slide, IdControl.getId());
        GuiDecision guiDecision = new GuiDecision(decision, guiSlide,x + 400, y, theme);

        // Add the decision
        slide.addDecision(decision);
        this.editorGame.connectDecisionAndRenderableDecision(decision, guiDecision);
    }

    public void testCreate(Slide slide, double x, double y, ThemeColours theme){
        IdControl.addOne();
        // Create the decision
        Decision decision = new Decision("I am a new decision", slide, IdControl.getId());
        GuiDecision guiDecision = new GuiDecision("");

        // Add the decision
        slide.addDecision(decision);
        this.editorGame.connectDecisionAndRenderableDecision(decision, guiDecision);
    }
}
