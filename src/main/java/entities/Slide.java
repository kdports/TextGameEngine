package entities;

import java.util.*;

public class Slide {
    private final int id;
    private final String prompt;
    public ArrayList<Decision> decisions;

    public Slide(int id, String prompt, ArrayList<Decision> decisions) {
        this.id = id;
        this.prompt = prompt;
        this.decisions = decisions;
    }

    public Slide(int id, String prompt) {
        this.id = id;
        this.prompt = prompt;
        this.decisions = new ArrayList<>();
    }

    public int getId() {
        return this.id;
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