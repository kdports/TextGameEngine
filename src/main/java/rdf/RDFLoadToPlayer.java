package main.java.rdf;
import main.java.entities.*;

public class RDFLoadToPlayer extends RDFLoad {
    @Override
    public void sendGame(Game game) {
        Player.playGame(game);
    }
}
