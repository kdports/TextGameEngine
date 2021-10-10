package main.java.entities;

import main.java.entities.Decision;

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

    public boolean addDecision(Decision decision) {
        if (decisions.contains(decision)){
            return false;
        }else {
            this.decisions.add(decision);
            return true;
        }
    }
}