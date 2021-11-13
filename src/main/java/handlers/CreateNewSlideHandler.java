package handlers;

import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import interfaces.RenderableSlide;
import javafx.scene.input.MouseEvent;
import client.GuiSlideExperiment;
import utils.IdControl;

public class CreateNewSlideHandler extends BaseHandler {
    public CreateNewSlideHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    public void execute(MouseEvent mouseEvent) {
        IdControl.addOne();
        Slide slide = this.studio.createSlide(IdControl.getId(), "this is a new slide!!!!!");
        GuiSlideExperiment GuiSlide = new GuiSlideExperiment(slide, 500, 500);
        this.editorGame.connectSlideAndRenderableSlide(slide, GuiSlide);
    }
}
