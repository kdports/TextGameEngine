
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
}




