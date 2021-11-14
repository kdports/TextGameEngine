package entities;

import java.util.ArrayList;

public class Game {
    public Slide firstSlide;
    private final ArrayList<Slide> slides;

    public Game(Slide firstSlide){
        this.firstSlide = firstSlide;
        this.slides = new ArrayList<Slide>();
    }

    public Game() {
        this.firstSlide = null;
        this.slides = new ArrayList<Slide>();
    }

    public boolean setFirstSlide(Slide slide) {
        this.firstSlide = slide;
        return true;
    }

    public Slide createSlide(int slideId, String text) {
        Slide newSlide = new Slide(slideId, text);
        this.addSlide(newSlide);

        return newSlide;
    }

    public boolean addSlide(Slide slide) {
        this.slides.add(slide);
        return true;
    }

    public ArrayList<Slide> getSlides() {
        return this.slides;
    }

    public boolean deleteSlide(Slide slide) {
        return this.slides.remove(slide);
    }

//    public void createDecision(String text) {
//        Decision d = new Decision(text);
////        this.addDecision(d);
//    }

    /**
     * Parent ---- d ---- <>
     * <> ---- d ---- Parent
     * */
    public boolean addDecision(Slide parentSlide, Decision decision) {
        return parentSlide.addDecision(decision);
    }
}