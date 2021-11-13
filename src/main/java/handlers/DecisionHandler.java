package handlers;

import entities.Decision;
import entities.EditorGame;
import entities.Studio;
import interfaces.RenderableDecision;
import javafx.scene.input.MouseEvent;

import java.util.Map;


    public class DecisionHandler extends BaseHandler {
        public DecisionHandler(Studio studio, EditorGame editorGame) {
            super(studio, editorGame);
        }

        public void endDrag() {
            //
        }

        public void editMessage(Decision decision, String message){
            decision.setText(message);
        }

        public int getId(Decision decision){
            return decision.id;
        }

        public void deleteDecision(Decision decision){
            editorGame.deleteDecision(decision, editorGame.getDecisionMap().get(decision));
        }


    }
