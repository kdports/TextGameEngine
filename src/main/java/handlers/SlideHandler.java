package handlers;

import client.GuiSlideExperiment;
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

    public void delete(Slide slide){
        editorGame.deleteSlide(slide, editorGame.getSlideMap().get(slide));
    };


    public void editMessage(Slide slide, String message){
        slide.setPrompt(message);
    }



    public void endDrag() {
        //
    }


    public void dropEvent(Slide slide, GuiSlideExperiment GuiSlide, String db) {
         StringBuilder desiredDecision = new StringBuilder(db);
         System.out.println(desiredDecision);
         desiredDecision.deleteCharAt(0);

         if (Character.toString(db.charAt(0)).equals("0")){
             editorGame.changeDecisionOrigin(slide, GuiSlide, Integer.parseInt(desiredDecision.toString()));

         }
         if (Character.toString(db.charAt(0)).equals("1")){
             editorGame.changeDecisionOutput(slide, GuiSlide, Integer.parseInt(desiredDecision.toString()));
         }
     }
}
