package handlers;

import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import interfaces.RenderableSlide;
import javafx.scene.input.MouseEvent;

import java.util.Map;

public class SlideHandler extends BaseHandler {
    public SlideHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    public void delete(Map.Entry<Slide, RenderableSlide> entry){
        editorGame.deleteSlide(entry);
    };


    public void beginDrag(Map.Entry<Slide, RenderableSlide> entry, MouseEvent event) {
        entry.getValue().setAnchorX(event.getX());
        entry.getValue().setAnchorY(event.getY());
    }

    public void drag(Map.Entry<Slide, RenderableSlide> entry, MouseEvent event) {
        entry.getValue().changeX(event.getSceneX() - entry.getValue().getAnchorX());
        entry.getValue().changeY(event.getSceneY() - entry.getValue().getAnchorY());
    }

    public void editMessage(Map.Entry<Slide, RenderableSlide> entry, String message){
        entry.getKey().setPrompt(message);
    }



    public void endDrag() {
        //
    }

    // public void dropEvent(Map.Entry<Slide, RenderableSlide> entry, String db) {
    //     StringBuilder desiredDecision = new StringBuilder(db);
    //     desiredDecision.deleteCharAt(0);
    //     if (Character.toString(db.charAt(0)).equals("0")){
    //         Decision decisionToChange = EditorGame.getDecisionById(Integer.parseInt(desiredDecision.toString()));
    //         decisionToChange.setOrigin(entry.getKey());
    //     }
    //     if (Character.toString(db.charAt(0)).equals("1")){
    //         Decision decisionToChange = EditorGame.getDecisionById(Integer.parseInt(desiredDecision.toString()));
    //         decisionToChange.setTarget(entry.getKey());
    //     }
    // }
}
