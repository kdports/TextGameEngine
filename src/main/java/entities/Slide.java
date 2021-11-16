package entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.*;

/**
 * Represents a slide in the game that displays text and has decisions that take you to another slide
 */
public class Slide {
    private final int id;
    private String prompt;
    public ArrayList<Decision> outgoingDecisions;
    private final BooleanProperty firstSlide = new SimpleBooleanProperty(false);
    private final StringProperty observablePrompt = new SimpleStringProperty();

    /**
     * Constructor that makes the slide
     *
     * @param id        id of the slide
     * @param prompt    The text of the slide
     * @param decisions The decisions that lead the slide to another slide
     */
    public Slide(int id, String prompt, ArrayList<Decision> decisions) {
        this.id = id;
        this.prompt = prompt;
        this.outgoingDecisions = decisions;
    }

    /**
     * Constructor that makes the slide
     *
     * @param id     id of the slide
     * @param prompt The text of the slide
     */
    public Slide(int id, String prompt) {
        this.id = id;
        this.prompt = prompt;
        this.outgoingDecisions = new ArrayList<>();
    }

    /**
     * Getter method for the id of the slide
     *
     * @return int - the id of the slide
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter method for the prompt of the slide
     *
     * @return String - The text of the slide
     */
    public String getPrompt() {
        return this.prompt;
    }

    /**
     * Sets the prompt of the slide
     *
     * @param prompt - the text to set the slide to
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
        observablePrompt.setValue(prompt);
    }

    /**
     * Sets the outgoingDecision
     *
     * @param outgoingDecisions - The ArrayList of decisions to set as outgoingDecistions
     */
    public void setOutgoingDecisions(ArrayList<Decision> outgoingDecisions) {
        this.outgoingDecisions = outgoingDecisions;
    }


    public void setAsFirstSlide(boolean toSet) {
        firstSlide.set(toSet);
    }

    public StringProperty getObservablePrompt() {
        return this.observablePrompt;
    }

    public BooleanProperty getObservableFirstSlide() {
        return this.firstSlide;
    }

    public boolean addDecision(Decision decision) {
        return this.outgoingDecisions.add(decision);
    }

    public boolean removeDecision(Decision decision) {
        return this.outgoingDecisions.remove(decision);
    }
}