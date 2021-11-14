package rdf;

import client.GameRenderer.GameRenderer;
import entities.Game;
import entities.Player;
import entities.Slide;
import interfaces.PlayDisplayer;
import org.apache.jena.rdf.model.Resource;

import java.io.FileNotFoundException;
import java.util.Map;

public class RDFLoadToPlayer extends RDFLoad {
    public RDFLoadToPlayer(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    public void sendGame(Game game) {
        PlayDisplayer dp = new GameRenderer();
        Player p = new Player(dp, game);
        p.playGame();
    }

    public Game loadGameFromFile() {
        Game game = new Game();

        for (Map.Entry<Resource, Slide> entry : this.slideNodeMap.entrySet()) {
            Resource slideNode = entry.getKey();
            Slide slide = entry.getValue();

            // Check if the slide is the first slide
            Resource categorizedAsObject = slideNode.getPropertyResourceValue(TGEO.categorizedAs);
            if (categorizedAsObject != null) {
                game.firstSlide = slide;
            }

            game.addSlide(slide);
        }

        return game;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // For debug purposes. Just run this, and if Player.playGame() is hooked up correctly, it should work.
        String rdfFilepath = "src/main/resources/rdf/scratch_game.ttl";
        RDFLoadToPlayer loader = new RDFLoadToPlayer(rdfFilepath);
        Game game = loader.loadGameFromFile();
        loader.sendGame(game);
    }


}
