package handlers;

import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import javafx.scene.input.MouseEvent;

import java.util.Map;


    public class DecisionHandler extends BaseHandler {
        public DecisionHandler(Studio studio, EditorGame editorGame) {
            super(studio, editorGame);
        }

        public void beginDrag(Map.Entry<Decision, RenderableDecision> entry, MouseEvent event) {
            entry.getValue().setAnchorX(event.getX());
            entry.getValue().setAnchorY(event.getY());
        }

        public void drag(Map.Entry<Decision, RenderableDecision> entry, MouseEvent event) {
            entry.getValue().changeX(event.getSceneX() - entry.getValue().getAnchorX());
            entry.getValue().changeY(event.getSceneY() - entry.getValue().getAnchorY());
        }

        public void endDrag() {
            //
        }

        public void editMessage(Map.Entry<Decision, RenderableDecision> entry, String message){
            entry.getKey().setText(message);
        }

        public int getId(Map.Entry<Decision, RenderableDecision> entry){
            return entry.getKey().id;
        }


    }
