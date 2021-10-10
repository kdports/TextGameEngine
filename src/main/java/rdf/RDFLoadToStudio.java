package main.java.rdf;
import main.java.entities.*;

public class RDFLoadToStudio extends RDFLoad {
    @Override
    public void sendGame(Game game) {
        Studio.loadGame(game);
    }
}
