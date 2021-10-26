package entities;

import client.DisplayerBoxScene;
import client.GameRenderer;
import interfaces.EditDisplayer;

import java.util.ArrayList;

public class Studio {
    public Game game = new Game();

    public void loadGame(Game loadedGame) {
        this.game = loadedGame;
    }

    public void playGame() {
        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, this.game);
        p.playGame();
    }

    public void createSlide(String text) {
        this.game.createSlide(text);
    }

    public ArrayList<Slide> getSlides() { return this.game.getSlides(); }

    public void addDecision(Slide parentSlide, Decision newDecision) {
        game.addDecision(parentSlide, newDecision);
    }
}
