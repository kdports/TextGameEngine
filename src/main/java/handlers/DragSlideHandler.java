package handlers;

import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import interfaces.RenderableSlide;
import javafx.scene.input.MouseEvent;
import utils.IdControl;

import java.util.Map;

public class DragSlideHandler extends BaseHandler {
    public DragSlideHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    public void beginDrag(Map.Entry<Slide, RenderableSlide> entry, MouseEvent event) {
        entry.getValue().setAnchorX(event.getX());
        entry.getValue().setAnchorY(event.getY());
    }

    public void drag(Map.Entry<Slide, RenderableSlide> entry, MouseEvent event) {
        entry.getValue().setX(event.getSceneX() - entry.getValue().getAnchorX());
        entry.getValue().setY(event.getSceneY() - entry.getValue().getAnchorY());
    }

    public void endDrag() {
        //
    }


}
