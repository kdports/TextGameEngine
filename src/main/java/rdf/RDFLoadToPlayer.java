package rdf;

import client.GameRenderer;
import entities.Game;
import entities.Player;
import interfaces.PlayDisplayer;

import java.io.FileNotFoundException;

public class RDFLoadToPlayer extends RDFLoad {
    public RDFLoadToPlayer(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    @Override
    public void sendGame(Game game) {
        PlayDisplayer dp = new GameRenderer();
        Player p = new Player(dp, game);
        p.playGame();
    }

    public static void main(String[] args) throws FileNotFoundException {
        // For debug purposes. Just run this, and if Player.playGame() is hooked up correctly, it should work.
        String rdfFilepath = "src/main/resources/rdf/scratch_game.ttl";
        RDFLoadToPlayer loader = new RDFLoadToPlayer(rdfFilepath);
        loader.loadFromFile();
    }
}
