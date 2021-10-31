package handlers;

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
        entry.getValue().setX(event.getSceneX() - entry.getValue().getAnchorX());
        entry.getValue().setY(event.getSceneY() - entry.getValue().getAnchorY());
    }

    public void editMessage(Map.Entry<Slide, RenderableSlide> entry, String message){
        entry.getKey().setPrompt(message);
    }

    public void endDrag() {
        //
    }

}
