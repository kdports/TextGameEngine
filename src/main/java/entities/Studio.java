package main.java.entities;

import main.java.client.GameRenderer;

public class Studio {
    public static Game game;

    public static void loadGame(Game loadedGame) {
        game = loadedGame;
    }

    public static void playGame() {
        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, game);
        p.playGame();
    }

    public static void addSlide(Slide newSlide) {
        game.addSlide(newSlide);
    }

    public static void addDecision(Slide parentSlide, Decision newDecision) {
        game.addDecision(parentSlide, newDecision);
    }
}
