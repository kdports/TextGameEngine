package entities;

import java.util.*;

public class Slide {
    public String prompt;
    public ArrayList<Decision> decisions;

    public Slide(String prompt) {
        this.prompt = prompt;
        this.decisions = new ArrayList<Decision>();
    }

    public Slide(String prompt, ArrayList<Decision> decisions) {
        this.prompt = prompt;
        this.decisions = decisions;
    }

    public boolean setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
        return true;
    }

    public boolean addDecision(Decision decision) {
        return this.decisions.add(decision);
    }

    public boolean removeDecision(Decision decision) {
        return this.decisions.remove(decision);
    }
}