package client;

import interfaces.EditDisplayer;
import entities.Studio;
import javafx.event.Event;
import javafx.event.EventHandler;

public class CreateNewSlideHandler implements EventHandler {
    private final Studio studio;
    private final EditDisplayer displayer;

    public CreateNewSlideHandler(EditDisplayer displayer, Studio studio) {
        this.displayer = displayer;
        this.studio = studio;
    }

    @Override
    public void handle(Event event) {
        displayer.createSlide();
        this.studio.createSlide("");
    }
}
