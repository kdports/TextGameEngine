package rdf;
import entities.*;

import java.io.FileNotFoundException;

public class RDFLoadToPlayer extends RDFLoad {
    public RDFLoadToPlayer(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    @Override
    public void sendGame(Game game) {
        Player.playGame(game);
    }
}
