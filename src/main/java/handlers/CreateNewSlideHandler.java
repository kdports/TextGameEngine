package handlers;

import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import javafx.scene.input.MouseEvent;
import client.GuiSlide;
import utils.IdControl;

public class CreateNewSlideHandler extends BaseHandler {
    public CreateNewSlideHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    public void execute(MouseEvent mouseEvent) {
        IdControl.addOne();
        Slide slide = this.studio.createSlide(IdControl.getId(), "I am a Slide!");
        GuiSlide GuiSlide = new GuiSlide(slide, 500, 500);
        this.editorGame.connectSlideAndRenderableSlide(slide, GuiSlide);
    }
}
