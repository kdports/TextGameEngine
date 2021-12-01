package handlers;

import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import javafx.scene.input.MouseEvent;
import client.GuiSlide.GuiSlide;
import utils.IdControl;

/**
 * A handler to deal with the creation of new slides.
 */
public class CreateNewSlideHandler extends BaseHandler {
    public CreateNewSlideHandler(Studio studio, EditorGame editorGame) {
        super(studio, editorGame);
    }

    public void execute(Double sceneX, double sceneY) {
        // Make sure there are no ID collisions.
        IdControl.addOne();

        // Create a slide with some default text
        Slide slide = this.studio.createSlide(IdControl.getId(), "A New Slide");
        GuiSlide GuiSlide = new GuiSlide(slide, sceneX + 500, sceneY + 500);

        // Add those two created objects into the data structure editorGame.
        this.editorGame.connectSlideAndRenderableSlide(slide, GuiSlide);
    }
}