package rdf;
import entities.*;

public class RDFLoadToPlayer extends RDFLoad {
    @Override
    public void sendGame(Game game) {
        Player.playGame(game);
    }
}
