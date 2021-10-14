package main.java.rdf;
import main.java.entities.*;

import java.io.FileNotFoundException;

public class RDFLoadToStudio extends RDFLoad {
    public RDFLoadToStudio(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    @Override
    public void sendGame(Game game) {
        Studio.loadGame(game);
    }
}
