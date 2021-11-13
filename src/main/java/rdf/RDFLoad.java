package rdf;
import client.GuiDecision;
import entities.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import client.GuiSlide;

public abstract class RDFLoad {
    private final Model model;
    private final HashMap<Resource, Slide> slideNodeMap = new HashMap<>();
    private final HashMap<Resource, Decision> decisionNodeMap = new HashMap<>();
    private final HashMap<Slide, GuiSlide> renderableSlideMap = new HashMap<>();
    private final HashMap<Decision, GuiDecision> renderableDecisionMap = new HashMap<>();

    public RDFLoad(String filepath) throws FileNotFoundException {
        this.model = ModelFactory.createDefaultModel();
        this.model.setNsPrefixes(Ontology.prefixes);

        // Read the TTL file into model
        FileInputStream in = new FileInputStream(filepath);
        this.model.read(in,null,"TTL");

        this.populateGameResourceMaps();
        this.populateRenderableResourceMaps();
    }

    /**
     * Populate the slideNodeMap and decisionNodeMap from the loaded model
     */
    private void populateGameResourceMaps() {
        // Populate slideNodeMap
        ResIterator slideIter = this.model.listResourcesWithProperty(RDF.type, TGEO.Slide);
        if (slideIter.hasNext()) {
            while (slideIter.hasNext()) {
                Resource slideNode = slideIter.nextResource();

                String slideText = slideNode.getProperty(TGEO.hasText).getString();
                Slide slide = new Slide((int) (Math.random() * 100000), slideText);

                this.slideNodeMap.put(slideNode, slide);
            }
        }

        // Populate decisionNodeMap
        ResIterator decisionIter = this.model.listResourcesWithProperty(RDF.type, TGEO.Decision);

        // For each decision, build a Decision instance
        if (decisionIter.hasNext()) {
            while(decisionIter.hasNext()) {
                Resource decisionNode = decisionIter.nextResource();

                String decisionText = decisionNode.getProperty(TGEO.hasText).getString();
                Resource originSlideNode = this.model.listResourcesWithProperty(TGEO.hasDecision, decisionNode).nextResource();
                Slide originSlide = slideNodeMap.get(originSlideNode);
                Resource targetSlideNode = decisionNode.getPropertyResourceValue(TGEO.directsTo);
                Slide targetSlide = slideNodeMap.get(targetSlideNode);
                Decision decision = new Decision(decisionText, originSlide, (int) (Math.random() * 100000), targetSlide);

                this.decisionNodeMap.put(decisionNode, decision);
            }
        }

        // Set slide decisions
        for (Map.Entry<Resource, Slide> entry : this.slideNodeMap.entrySet()) {
            entry.getValue().setOutgoingDecisions(this.getDecisionsOfSlide(entry.getKey()));
        }

        // Set decision targets
        for (Map.Entry<Resource, Decision> entry : this.decisionNodeMap.entrySet()) {
            Resource decisionNode = entry.getKey();
            Decision decision = entry.getValue();

            Resource targetSlideNode = decisionNode.getPropertyResourceValue(TGEO.directsTo);
            decision.setTarget(this.slideNodeMap.get(targetSlideNode));
        }
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

    private ArrayList<Decision> getDecisionsOfSlide(Resource slideNode) {
        // Get the decisions hanging off of param slideNode
        NodeIterator iter = this.model.listObjectsOfProperty(slideNode, TGEO.hasDecision);
        ArrayList<RDFNode> results = (ArrayList<RDFNode>) iter.toList();

        // For each decision, build a Decision instance
        ArrayList<Decision> decisions = new ArrayList<>();
        for (RDFNode decisionNode : results) {
            // Get decision text
            if (decisionNode instanceof Resource) {
                decisions.add(this.decisionNodeMap.get(decisionNode));
            }
        }

        return decisions;
    }

    public void loadFromFile() {
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

        sendGame(game);
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

    public abstract void sendGame(Game game);
}
