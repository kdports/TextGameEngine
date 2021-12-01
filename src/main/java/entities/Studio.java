package entities;

import client.DisplayGame.GameRenderer;

import java.util.ArrayList;

/**
 * Holds the game instance that the GUI communicates with
 */
public class Studio {
    public Game game = new Game();

//    public void loadGame(Game loadedGame) {
//        this.game = loadedGame;
//    }
//
//    public void playGame() {
//        GameRenderer gr = new GameRenderer();
//        Player p = new Player(gr, this.game);
//        p.playGame();
//    }

    /**
     * Creates a slide instance with the given ID and dialogue
     *
     * @param slideId - The ID of a slide
     * @param text - The dialogue of a slide
     * @return - A newly created slide
     */
    public Slide createSlide(int slideId, String text) {
        return this.game.createSlide(slideId, text);
    }

    /**
     * Gets the slides that are currently in the game instance
     *
     * @return - An arraylist of slides from the game.
     */
    public ArrayList<Slide> getSlides() { return this.game.getSlides(); }

//    public void createDecision(Slide parentSlide, Decision newDecision) {
//        this.game.createDecision("text");
//    }
}
