package handlers;

import client.GuiDecision;
import client.GuiSlide;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import entities.Decision;
import utils.IdControl;

public class CreateNewDecisionHandler extends BaseHandler {
    public CreateNewDecisionHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }
    public void create(Slide slide, GuiSlide GuiSlide, double x, double y){
        IdControl.addOne();
        Decision decision = new Decision("I am a new decision", slide, IdControl.getId());
        GuiDecision GuiDecision = new GuiDecision(decision, GuiSlide, x + 400, y);
        slide.addDecision(decision);
        this.editorGame.connectDecisionAndRenderableDecision(decision, GuiDecision);
    }
}
