package handlers;

import client.GuiDecisionExperiment;
import client.GuiSlideExperiment;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import entities.Decision;
import utils.IdControl;


import java.util.Map;

public class CreateNewDecisionHandler extends BaseHandler {
    public CreateNewDecisionHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }
    public void create(Slide slide, GuiSlideExperiment GuiSlide, double x, double y){
        IdControl.addOne();
        Decision decision = new Decision("I am a new decision", slide, IdControl.getId());
        GuiDecisionExperiment GuiDecision = new GuiDecisionExperiment(decision, GuiSlide, x + 400, y);
        slide.addDecision(decision);
        this.editorGame.connectDecisionAndRenderableDecision(decision, GuiDecision);
    };
}
