package main.java.entities;

import java.util.ArrayList;

public class Game {
    public Slide firstSlide;
    public ArrayList<Slide> slides;

    public Game(Slide firstSlide){
        this.firstSlide = firstSlide;
        this.slides = new ArrayList<Slide>();
    }

    public Game() {
        this.firstSlide = null;
        this.slides = new ArrayList<Slide>();
    }

    public boolean addSlide(Slide slide) {
        this.slides.add(slide);
        return true;
    }

    public boolean addDecision(Slide parentSlide, Decision decision) {
        return parentSlide.addDecision(decision);
    }
}