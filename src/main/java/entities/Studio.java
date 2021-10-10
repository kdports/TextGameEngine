package main.java.entities;

public class Studio {
    public static Game game;

    public static void loadGame(Game loadedGame) {
        game = loadedGame;
    }

    public static void playGame() {
        Player.playGame(game);
    }

    public static void addSlide(Slide newSlide) {
        game.addSlide(newSlide);
    }

    public static void addDecision(Slide parentSlide, Decision newDecision) {
        game.addDecision(parentSlide, newDecision);
    }
}
