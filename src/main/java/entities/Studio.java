package entities;

import client.GameRenderer;

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

    public Slide createSlide(int slideId, String text) {
        return this.game.createSlide(slideId, text);
    }

    public ArrayList<Slide> getSlides() { return this.game.getSlides(); }

//    public void createDecision(Slide parentSlide, Decision newDecision) {
//        this.game.createDecision("text");
//    }
}
