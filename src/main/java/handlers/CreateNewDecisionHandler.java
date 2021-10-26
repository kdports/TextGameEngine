package handlers;

import interfaces.EditDisplayer;
import entities.Studio;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CreateNewDecisionHandler implements EventHandler {
    private final Studio studio;
    private final EditDisplayer displayer;
    private final int idControl;

    public CreateNewDecisionHandler(EditDisplayer displayer, Studio studio, int idControl) {
        this.displayer = displayer;
        this.studio = studio;
        this.idControl = idControl;
    }

    @Override
    public void handle(Event event) {
        this.displayer.createDecision();
        this.studio.createSlide(this.idControl,"Insert choice here");
    }
}
