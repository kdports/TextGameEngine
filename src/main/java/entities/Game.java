package entities;


import java.util.ArrayList;

/**
 * Represents the playable game class
 */
public class Game {
    public Slide firstSlide;
    private final ArrayList<Slide> slides;

    /**
     * Constructs a game with a first slide
     *
     * @param firstSlide - The first slide of the game
     */
    public Game(Slide firstSlide) {
        this.firstSlide = firstSlide;
        this.slides = new ArrayList<>();
    }

    /**
     * Constructs a game without a first slide
     */
    public Game() {
        this.firstSlide = null;
        this.slides = new ArrayList<>();
    }

    /**
     * Sets the first slide of the game to a slide
     *
     * @param slide - The slide to set as first slide
     * @return true
     */
    public boolean setFirstSlide(Slide slide) {
        this.firstSlide = slide;
        return true;
    }

    /**
     * Creates a new slide
     *
     * @param slideId - The id of the slide
     * @param text    - The text of the slide
     * @return Slide - The slide that got created
     */
    public Slide createSlide(int slideId, String text) {
        Slide newSlide = new Slide(slideId, text);
        this.addSlide(newSlide);

        return newSlide;
    }

    /**
     * Adds a slide to the game
     *
     * @param slide - The slide to add
     * @return true
     */
    public boolean addSlide(Slide slide) {
        this.slides.add(slide);
        return true;
    }

    /**
     * Gets the ArrayList of slides
     *
     * @return ArrayList<Slide> - The ArrayList of Slides
     */
    public ArrayList<Slide> getSlides() {
        return this.slides;
    }

    /**
     * Deletes the slide
     *
     * @param slide - Slide to remove
     * @return boolean - Returns true if the slide was removed
     */
    public boolean deleteSlide(Slide slide) {
        return this.slides.remove(slide);
    }

    /**
     * Adds a decision to a slide
     *
     * @param parentSlide - Slide to add the decision to
     * @param decision    - Decision to add
     * @return true
     */
    public boolean addDecision(Slide parentSlide, Decision decision) {
        return parentSlide.addDecision(decision);
    }
}