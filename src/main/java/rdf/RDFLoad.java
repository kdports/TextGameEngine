package rdf;
import entities.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class RDFLoad {
    private Model model;
    public RDFLoad(String filepath) throws FileNotFoundException {
        this.model = ModelFactory.createDefaultModel();
        this.model.setNsPrefixes(Ontology.prefixes);

        // Read the TTL file into model
        FileInputStream in = new FileInputStream(filepath);
        this.model.read(in,null,"TTL");
    }

    private ArrayList<Decision> getDecisionsOfSlide(Resource slideNode) {
        // Get the decisions hanging off of param slideNode
        NodeIterator iter = this.model.listObjectsOfProperty(slideNode, TGEO.hasDecision);
        ArrayList<RDFNode> results = (ArrayList<RDFNode>) iter.toList();

        // For each decision, build a Decision instance
        ArrayList<Decision> decisions = new ArrayList<>();
        for (RDFNode decisionNode : results) {
            // Get decision text
            if (decisionNode instanceof Resource) {
                String decisionText = ((Resource) decisionNode).getProperty(TGEO.hasText).getString();
                Decision d = new Decision(decisionText);
                decisions.add(d);
            }
        }
        return decisions;
    }

    public void loadFromTrig(String filepath) throws FileNotFoundException {
        Game game = new Game();

        // Find and add all Slides
        ResIterator iter = this.model.listResourcesWithProperty(RDF.type, TGEO.Slide);
        if (iter.hasNext()) {
            System.out.println("The database contains slides for:");
            while (iter.hasNext()) {
                Resource slideNode = iter.nextResource();
                String slideText = slideNode.getProperty(TGEO.hasText).getString();

                ArrayList<Decision> decisions = getDecisionsOfSlide(slideNode);
                Slide slide = new Slide(slideText, decisions);

                // Add the slide as the target after the fact
                for (Decision d : decisions) {
                    d.addTarget(slide);
                }

                // Add as first slide if there aren't slides yet
                if (game.firstSlide == null) {
                    game.firstSlide = slide;
                }
                game.addSlide(slide);
            }
        } else {
            System.out.println("No slides were found in the database");
        }

        sendGame(game);
    }

    public abstract void sendGame(Game game);

    public static void main(String[] args) throws FileNotFoundException {
        // For debug purposes. Just run this, and if Player.playGame() is hooked up correctly, it should work.
        String rdfFilepath = "src/main/java/rdf/scratch_game.ttl";
        RDFLoad loader = new RDFLoadToPlayer(rdfFilepath);
        loader.loadFromTrig(rdfFilepath);
    }
}
