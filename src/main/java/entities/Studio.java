package entities;

import client.GUIScene;
import client.GameRenderer;

import java.util.ArrayList;

public class Studio {
    public static Game game = new Game();

    public static void loadGame(Game loadedGame) {
        game = loadedGame;
    }

    public static void playGame() {
        GameRenderer gr = new GameRenderer();
        Player p = new Player(gr, game);
        p.playGame();
    }

    public static void addSlide(Slide newSlide) { game.addSlide(newSlide); }

    public static ArrayList getSlides() { return game.getSlides(); }

    public static void addDecision(Slide parentSlide, Decision newDecision) {
        game.addDecision(parentSlide, newDecision);
    }
}
