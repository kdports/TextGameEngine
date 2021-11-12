
package rdf;
import client.RootDisplayerExperiment;
import entities.*;
import org.apache.jena.vocabulary.RDF;

import java.io.FileNotFoundException;

public class RDFLoadToStudio extends RDFLoad {
    public RDFLoadToStudio(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    @Override
    public void sendGame(Game game) {

    }

    public static void main(String[] args) throws FileNotFoundException {
        String rdfFilepath = "src/main/resources/rdf/scratch_game.ttl";
        RootDisplayerExperiment gui = new RootDisplayerExperiment();
        gui.begin(args, rdfFilepath);

    }
}




