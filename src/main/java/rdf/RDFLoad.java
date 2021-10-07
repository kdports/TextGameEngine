package rdf;
import entities.*;

public abstract class RDFLoad {
    public void loadFromTrig(String filepath) {
        // Maybe set filepath to default??? no options??
        // Do some loading
        Game game = new Game();
        // game.addDeicsion();
        // game.addSlide();
        sendGame(game);
    }

    public abstract void sendGame(Game game);
}
