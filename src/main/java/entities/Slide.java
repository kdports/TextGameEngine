package entities;

import entities.Decision;

import java.util.*;

public class Slide {
    public String prompt;
    public ArrayList<Decision> decisions;

    public Slide(String prompt, ArrayList<Decision> decisions) {
        this.prompt = prompt;
        this.decisions = decisions;
    }

    public boolean addDecision(Decision decision) {
        this.decisions.add(decision);
        return true;
    }
}