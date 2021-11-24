package handlers;

import entities.EditorGame;
import entities.Studio;

import java.io.FileNotFoundException;

/**
 * A wrapper for all the handlers, so that we can access them all in a single object. Holds references to all the
 * handlers that are defined in this project.
 */
public class Handlers {
    public static CreateNewSlideHandler createNewSlideHandler;
    public static SlideHandler slideHandler;
    public static CreateNewDecisionHandler createNewDecisionHandler;
    public static DecisionHandler decisionHandler;

    /**
     * Creates an instance of each handler.
     *
     * @param editorGame - The EditorGame instance that the handlers influence
     */
    public Handlers(EditorGame editorGame) {
        Studio studio = new Studio();

        createNewSlideHandler = new CreateNewSlideHandler(studio, editorGame);
        slideHandler = new SlideHandler(studio, editorGame);
        createNewDecisionHandler = new CreateNewDecisionHandler(studio, editorGame);
        decisionHandler = new DecisionHandler(studio, editorGame);
    }
}
