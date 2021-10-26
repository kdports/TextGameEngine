package entities;

import java.util.*;

public class Slide {
    public String prompt;
    public ArrayList<Decision> decisions;

    public Slide(String prompt) {
        this.prompt = prompt;
        this.decisions = new ArrayList<>();
    }

    public Slide(String prompt, ArrayList<Decision> decisions) {
        this.prompt = prompt;
        this.decisions = decisions;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }

    public boolean addDecision(Decision decision) {
        return this.decisions.add(decision);
    }

    public boolean removeDecision(Decision decision) {
        return this.decisions.remove(decision);
    }
}