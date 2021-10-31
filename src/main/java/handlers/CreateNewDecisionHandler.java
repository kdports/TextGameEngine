package handlers;

import entities.EditorGame;
import entities.Slide;
import interfaces.EditDisplayer;
import entities.Studio;
import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import javafx.event.Event;
import javafx.event.EventHandler;
import entities.Decision;
import utils.IdControl;


import java.util.Map;

public class CreateNewDecisionHandler extends BaseHandler {
    public CreateNewDecisionHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }
    public void create(Map.Entry<Slide, RenderableSlide> entry){
        IdControl.addOne();
        Decision decision = new Decision("I am a new decision", entry.getKey(), IdControl.getId());
        RenderableDecision renderableDecision = new RenderableDecision(entry.getValue().getX(), entry.getValue().getY());
        entry.getKey().addDecision(decision);
        this.editorGame.connectDecisionAndRenderableDecision(decision, renderableDecision);
    };
}
