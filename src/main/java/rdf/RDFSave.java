package rdf;

import client.GuiDecision;
import client.GuiSlide;
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
    public void saveToTrig(EditorGame editorGame, String filepath) throws FileNotFoundException {
        // Use Jena Apache to save to Trig.

        // Create model
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefixes(Ontology.prefixes);

        // Add all slides with their ID to the model
        for (Map.Entry<Slide, GuiSlide> entry : editorGame.getAllEntriesSlide()){
            Slide currSlide = entry.getKey();
            Resource currSlideNode = model.createResource(((Integer) currSlide.getId()).toString());
            currSlideNode.addProperty(RDF.type, TGEO.Slide);
        }

        // ^^ with decisions
        for (Map.Entry<Decision, GuiDecision> entry : editorGame.getAllEntriesDecision()){
            Decision currDecision = entry.getKey();
            Resource currDecisionNode = model.createResource(((Integer) currDecision.getId()).toString());
            currDecisionNode.addProperty(RDF.type, TGEO.Decision);
        }

        // Add attributes to slides
        for (Map.Entry<Slide, GuiSlide> entry : editorGame.getAllEntriesSlide()){
            Slide currSlide = entry.getKey();
            GuiSlide guiSlide = entry.getValue();
            Resource currSlideNode = model.getResource(((Integer) currSlide.getId()).toString());

            currSlideNode.addProperty(TGEO.hasXLocation, String.valueOf(guiSlide.getLayoutX()));
            currSlideNode.addProperty(TGEO.hasYLocation, String.valueOf(guiSlide.getLayoutY()));
            currSlideNode.addProperty(TGEO.hasText, currSlide.getPrompt());
            for (Decision decision : currSlide.outgoingDecisions){
                currSlideNode.addProperty(TGEO.hasDecision, model.getResource(String.valueOf(decision.id)));
            }
        }

        // Add attributes to decisions
        for (Map.Entry<Decision, GuiDecision> entry : editorGame.getAllEntriesDecision()){
            Decision currDecision = entry.getKey();
            GuiDecision guiDecision = entry.getValue();
            Resource currDecisionNode = model.getResource(((Integer) currDecision.getId()).toString());

            currDecisionNode.addProperty(TGEO.hasXLocation, String.valueOf(guiDecision.getLayoutX()));
            currDecisionNode.addProperty(TGEO.hasYLocation, String.valueOf(guiDecision.getLayoutY()));
            currDecisionNode.addProperty(TGEO.hasText, currDecision.getText());
            currDecisionNode.addProperty(TGEO.directsTo, model.getResource(String.valueOf(currDecision.target.getId())));

        }

        // Save to file
        OutputStream out = new FileOutputStream(filepath);
        model.write(out, "Turtle");
    }
}

