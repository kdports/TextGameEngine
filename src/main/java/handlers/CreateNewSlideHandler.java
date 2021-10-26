package handlers;

import entities.EditorGame;
import entities.Slide;
import interfaces.EditDisplayer;
import entities.Studio;
import interfaces.RenderableSlide;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import utils.IdControl;

public class CreateNewSlideHandler extends BaseHandler {
    public CreateNewSlideHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    public void execute(MouseEvent mouseEvent) {
        IdControl.addOne();
        Slide slide = this.studio.createSlide(IdControl.getId(), "this is a new slide!!!!!");
        RenderableSlide renderableSlide = new RenderableSlide(1920, 1080);
        this.editorGame.connectSlideAndRenderableSlide(slide, renderableSlide);
    }
}
