package handlers;

import entities.EditorGame;
import entities.Studio;

public class Handlers {
    public static CreateNewSlideHandler createNewSlideHandler;
    public static SlideHandler slideHandler;
    public static CreateNewDecisionHandler createNewDecisionHandler;
    public static DecisionHandler decisionHandler;

    public Handlers(EditorGame editorGame) {
        Studio studio = new Studio();

        createNewSlideHandler = new CreateNewSlideHandler(studio, editorGame);
        slideHandler = new SlideHandler(studio, editorGame);
        createNewDecisionHandler = new CreateNewDecisionHandler(studio, editorGame);
        decisionHandler = new DecisionHandler(studio, editorGame);
    }
}
