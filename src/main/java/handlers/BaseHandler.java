package handlers;

import entities.EditorGame;
import entities.Studio;

public class BaseHandler {
    protected final Studio studio;
    protected final EditorGame editorGame;

    public BaseHandler(Studio studio, EditorGame editorGame) {
        this.studio = studio;
        this.editorGame = editorGame;
    }
}
