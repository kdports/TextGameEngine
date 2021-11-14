package rdf;
import client.GuiDecision;
import client.GuiSlide;
import entities.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class RDFLoadToStudio extends RDFLoad {
    private final HashMap<Slide, GuiSlide> renderableSlideMap = new HashMap<>();
    private final HashMap<Decision, GuiDecision> renderableDecisionMap = new HashMap<>();

    public RDFLoadToStudio(String filepath) throws FileNotFoundException {
        super(filepath);
        this.populateRenderableResourceMaps();
    }

    private void populateRenderableResourceMaps(){
        ResIterator slideIter = this.model.listResourcesWithProperty(RDF.type, TGEO.Slide);
        if (slideIter.hasNext()) {
            while (slideIter.hasNext()) {
                Resource slideNode = slideIter.nextResource();

                double locationX = slideNode.getProperty(TGEO.hasXLocation).getDouble();
                double locationY = slideNode.getProperty(TGEO.hasYLocation).getDouble();

                Slide slide = slideNodeMap.get(slideNode);

                GuiSlide guiSlide = new GuiSlide(slide, locationX, locationY);
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
                GuiSlide renderableOrigin = renderableSlideMap.get(originSlide);

                GuiDecision guiDecision = new GuiDecision(decision, renderableOrigin, locationX, locationY);
                this.renderableDecisionMap.put(decision, guiDecision);
            }
        }

    }

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




