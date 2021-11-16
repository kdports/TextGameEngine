package handlers;

import entities.EditorGame;
import entities.Studio;

/**
 * The parent class of all handlers.
 */
public class  BaseHandler {
    protected final Studio studio;
    protected final EditorGame editorGame;

    public BaseHandler(Studio studio, EditorGame editorGame) {
        this.studio = studio;
        this.editorGame = editorGame;
    }
}
