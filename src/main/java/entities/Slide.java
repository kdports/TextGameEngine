package entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.*;

public class Slide {
    private final int id;
    private String prompt;
    public ArrayList<Decision> outgoingDecisions;

    public Slide(int id, String prompt, ArrayList<Decision> decisions) {
        this.id = id;
        this.prompt = prompt;
        this.outgoingDecisions = decisions;
    }

    public Slide(int id, String prompt) {
        this.id = id;
        this.prompt = prompt;
        this.outgoingDecisions = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
        observablePrompt.setValue(prompt);
    }

    public void setOutgoingDecisions(ArrayList<Decision> outgoingDecisions) {
        this.outgoingDecisions = outgoingDecisions;
    }

    public void setAsFirstSlide(boolean yes){
        firstSlide.set(yes);
    }

    private final BooleanProperty firstSlide = new SimpleBooleanProperty(false);

    private final StringProperty observablePrompt = new SimpleStringProperty();

    public StringProperty returnObservable(){return this.observablePrompt;}

    public BooleanProperty returnFirstSlide(){return this.firstSlide;}

    public boolean addDecision(Decision decision) {
        return this.outgoingDecisions.add(decision);
    }

    public boolean removeDecision(Decision decision) {
        return this.outgoingDecisions.remove(decision);
    }
}