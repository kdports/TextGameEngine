package rdf;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import entities.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Saves the data from an instance of EditorGame to a file that can be loaded in
 * later.
 */
public class RDFSave {

    /**
     * Saves the data from the editor to a file in a file path chosen by the user.
     *
     * @param editorGame - The EditorGame instance containing all slide and decisions data
     * @param filepath - The path in which the ttl file will be saved to
     */
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

            if (currSlide == editorGame.firstSlide) {
                currSlideNode.addProperty(TGEO.categorizedAs, TGEO.isFirst);
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

            if (currDecision.hasItemToGive()) {
                currDecisionNode.addProperty(TGEO.givesItem, currDecision.getItemToGive());
            }

            // -------------- ADDING CONDITIONALS DECISIONS
            HashSet<Decision> conditionals = currDecision.getDecisionConditionals();
            ArrayList<Decision> reliesOn = new ArrayList<>();
            for (Map.Entry<Decision, GuiDecision> entry2 : editorGame.getAllEntriesDecision()){
                if (conditionals.contains(entry2.getKey())){
                    reliesOn.add(entry2.getKey());
                }
            }
            currDecisionNode.addProperty(TGEO.requiresDecision, String.valueOf(reliesOn));

            //-------------- ADDING CONDITIONAL ITEMS ----------
            // get all items in game
            ArrayList<String> items = new ArrayList<>();
            for (Map.Entry<Decision, GuiDecision> entry2 : editorGame.getAllEntriesDecision()){
                if (entry2.getKey().hasItemToGive()) {
                    if (currDecision.getItemConditionals().contains(entry2.getKey().getItemToGive())){
                        items.add(entry2.getKey().getItemToGive());
                    }
                }
            }
            currDecisionNode.addProperty(TGEO.requiresItem, String.valueOf(items));
            // SAVE THE ITEM AND DECISION HERE
            //currDecisionNode.addProperty(TGEO.requiresItem, currDecision.)

        }

        // Save to file
        OutputStream out = new FileOutputStream(filepath);
        model.write(out, "Turtle");
    }

    public boolean isMalformedGame(EditorGame editorGame, String filepath) {
        if (editorGame.getAllEntriesDecision().isEmpty()) {
            return true;
        }
        if (editorGame.getAllEntriesSlide().isEmpty()) {
            return true;
        }
        if (filepath.isEmpty()) {
            return true;
        }
        return false;
    }
}

