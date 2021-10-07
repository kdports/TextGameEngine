package rdf;
import entities.*;

public class RDFLoadToStudio extends RDFLoad {
    @Override
    public void sendGame(Game game) {
        Studio.loadGame(game);
    }
}
