package rdf;

import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import client.ThemeColours;
import entities.*;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Populates hashmaps of slides that can be displayed in the editor. Uses the data
 * from the parent class' hashmaps as well in order to load a file back into the editor.
 */
public class RDFLoadToStudio extends RDFLoad {
    private final HashMap<Slide, GuiSlide> renderableSlideMap = new HashMap<>();
    private final HashMap<Decision, GuiDecision> renderableDecisionMap = new HashMap<>();

    /**
     * Takes a filepath and populates the resource maps with the data from the file
     *
     * @param filepath - The path of the file used to load data into the editor
     */
    public RDFLoadToStudio(String filepath) throws FileNotFoundException {
        super(filepath);
        this.populateRenderableResourceMaps();
    }

    /**
     * Populate the resource maps related to the Studio editor with data and locations of
     * slides and decisions.
     */
    private void populateRenderableResourceMaps(){
        ResIterator slideIter = this.model.listResourcesWithProperty(RDF.type, TGEO.Slide);

        // Add each slide and its editor counterpart to a hashmap
        if (slideIter.hasNext()) {
            while (slideIter.hasNext()) {
                Resource slideNode = slideIter.nextResource();

                double locationX = slideNode.getProperty(TGEO.hasXLocation).getDouble();
                double locationY = slideNode.getProperty(TGEO.hasYLocation).getDouble();

                Slide slide = slideNodeMap.get(slideNode);

                // EDIT THE THEME ABILITY IN RDF HERE
                GuiSlide guiSlide = new GuiSlide(slide, locationX, locationY, new ThemeColours());
                this.renderableSlideMap.put(slide, guiSlide);
            }
        }

        // Populate decisionNodeMap
        ResIterator decisionIter = this.model.listResourcesWithProperty(RDF.type, TGEO.Decision);

        // For each decision, build a Decision instance
        if (decisionIter.hasNext()) {
            while(decisionIter.hasNext()) {
                Resource decisionNode = decisionIter.nextResource();

                double locationX = decisionNode.getProperty(TGEO.hasXLocation).getDouble();
                double locationY = decisionNode.getProperty(TGEO.hasYLocation).getDouble();
                Decision decision = decisionNodeMap.get(decisionNode);
                Slide originSlide = decision.origin;
                Slide targetSlide = decision.target;
                GuiSlide renderableOrigin = renderableSlideMap.get(originSlide);
                GuiSlide renderableTarget = renderableSlideMap.get(targetSlide);

                GuiDecision guiDecision = new GuiDecision(decision, renderableOrigin, locationX, locationY, new ThemeColours());

                guiDecision.targetSlide = renderableTarget;
                guiDecision.rightLine.setSlide(guiDecision.targetSlide);

                guiDecision.targetSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> guiDecision.rightLine.recalculateX());
                guiDecision.targetSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> guiDecision.rightLine.recalculateY());
                guiDecision.rightLine.recalculateY();
                guiDecision.rightLine.recalculateX();

                this.renderableDecisionMap.put(decision, guiDecision);
            }
        }

    }

    /**
     * Creates a new EditorGame instance and populates it with data from our
     * HashMaps
     *
     * @return - The EditorGame instance that will be displayed in the studio
     */
    public EditorGame loadEditorGameFromFile() {
        EditorGame editorGame = new EditorGame();


        for (Map.Entry<Slide, GuiSlide> entry : this.renderableSlideMap.entrySet()) {
            editorGame.connectSlideAndRenderableSlide(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Decision, GuiDecision> entry : this.renderableDecisionMap.entrySet()) {
            editorGame.connectDecisionAndRenderableDecision(entry.getKey(), entry.getValue());
        }
        return editorGame;
    }
}




