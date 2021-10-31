package handlers;

import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import javafx.scene.input.MouseEvent;

import java.util.Map;


    public class DragDecisionHandler extends BaseHandler {
        public DragDecisionHandler(Studio studio, EditorGame editorGame) {
            super(studio, editorGame);
        }

        public void beginDrag(Map.Entry<Decision, RenderableDecision> entry, MouseEvent event) {
            entry.getValue().setAnchorX(event.getX());
            entry.getValue().setAnchorY(event.getY());
        }

        public void drag(Map.Entry<Decision, RenderableDecision> entry, MouseEvent event) {
            entry.getValue().setX(event.getSceneX() - entry.getValue().getAnchorX());
            entry.getValue().setY(event.getSceneY() - entry.getValue().getAnchorY());
        }

        public void endDrag() {
            //
        }


    }
