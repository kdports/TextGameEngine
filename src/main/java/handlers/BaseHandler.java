package handlers;

import entities.EditorGame;
import entities.Slide;
import entities.Studio;
import interfaces.RenderableSlide;
import javafx.scene.input.MouseEvent;
import utils.IdControl;

public class BaseHandler {
    protected final Studio studio;
    protected final EditorGame editorGame;

    public BaseHandler(Studio studio, EditorGame editorGame) {
        this.studio = studio;
        this.editorGame = editorGame;
    }
}
