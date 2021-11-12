package rdf;

import client.GuiDecisionExperiment;
import client.GuiSlideExperiment;
import client.RootDisplayerExperiment;
import entities.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

public class RDFSave {
    private Model model;

    public static void main(String[] args) throws FileNotFoundException {
        String rdfFilepath = "src/main/resources/rdf/scratch_game.ttl";
        RDFLoadToStudio loader = new RDFLoadToStudio(rdfFilepath);
        EditorGame loadedEditorGame = loader.loadEditorGameFromFile();
        RDFSave saver = new RDFSave();
        saver.saveToTrig(loadedEditorGame);
    }

    public void saveToTrig(EditorGame editorGame) throws FileNotFoundException {
        // Use Jena Apache to save to Trig.

        // Create model
        this.model = ModelFactory.createDefaultModel();
        this.model.setNsPrefixes(Ontology.prefixes);

        // Add all slides with their ID to the model
        for (Map.Entry<Slide, GuiSlideExperiment> entry : editorGame.getAllEntriesSlide()){
            Slide currSlide = entry.getKey();
            Resource currSlideNode = this.model.createResource(((Integer) currSlide.getId()).toString());
            currSlideNode.addProperty(RDF.type, TGEO.Slide);
        }

        // ^^ with decisions
        for (Map.Entry<Decision, GuiDecisionExperiment> entry : editorGame.getAllEntriesDecision()){
            Decision currDecision = entry.getKey();
            Resource currDecisionNode = this.model.createResource(((Integer) currDecision.getId()).toString());
            currDecisionNode.addProperty(RDF.type, TGEO.Decision);
        }

        // Add attributes to slides
        for (Map.Entry<Slide, GuiSlideExperiment> entry : editorGame.getAllEntriesSlide()){
            Slide currSlide = entry.getKey();
            GuiSlideExperiment guiSlide = entry.getValue();
            Resource currSlideNode = this.model.getResource(((Integer) currSlide.getId()).toString());

            currSlideNode.addProperty(TGEO.hasXLocation, String.valueOf(guiSlide.getLayoutX()));
            currSlideNode.addProperty(TGEO.hasYLocation, String.valueOf(guiSlide.getLayoutY()));
            currSlideNode.addProperty(TGEO.hasText, currSlide.getPrompt());
            for (Decision decision : currSlide.outgoingDecisions){
                currSlideNode.addProperty(TGEO.hasDecision, this.model.getResource(String.valueOf(decision.id)));
            }
        }

        // Add attributes to decisions
        for (Map.Entry<Decision, GuiDecisionExperiment> entry : editorGame.getAllEntriesDecision()){
            Decision currDecision = entry.getKey();
            GuiDecisionExperiment guiDecision = entry.getValue();
            Resource currDecisionNode = this.model.getResource(((Integer) currDecision.getId()).toString());

            currDecisionNode.addProperty(TGEO.hasXLocation, String.valueOf(guiDecision.getLayoutX()));
            currDecisionNode.addProperty(TGEO.hasYLocation, String.valueOf(guiDecision.getLayoutY()));
            currDecisionNode.addProperty(TGEO.hasText, currDecision.getText());
            currDecisionNode.addProperty(TGEO.directsTo, this.model.getResource(String.valueOf(currDecision.target.getId())));

        }

        // Save to file
        OutputStream out = new FileOutputStream("output.ttl");
        this.model.write(out, "TTL");
    }
}

