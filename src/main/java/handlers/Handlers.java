package handlers;

import entities.EditorGame;
import entities.Studio;

public class Handlers {
    public static CreateNewSlideHandler createNewSlideHandler;
    public static DragSlideHandler dragSlideHandler;

    public Handlers(EditorGame editorGame) {
        Studio studio = new Studio();

        createNewSlideHandler = new CreateNewSlideHandler(studio, editorGame);
        dragSlideHandler = new DragSlideHandler(studio, editorGame);
    }
}
