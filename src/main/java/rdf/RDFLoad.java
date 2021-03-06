package rdf;

import entities.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Parent class to RDFLoadToStudio and RDFLoadToPlayer. Gathers data from
 * a file and parses the data into hashmaps of Resources to Slides/Decisions
 */
public abstract class RDFLoad {
    protected final Model model;
    protected final HashMap<Resource, Slide> slideNodeMap = new HashMap<>();
    protected final HashMap<Resource, Decision> decisionNodeMap = new HashMap<>();

    /**
     * Takes data from a file and converts it into accessible HashMaps to be used
     * in the editor.
     *
     * @param filepath - The path of the ttl file that loads into the editor
     */
    public RDFLoad(String filepath) throws FileNotFoundException {
        this.model = ModelFactory.createDefaultModel();
        this.model.setNsPrefixes(Ontology.prefixes);

        // Read the TTL file into model
        FileInputStream in = new FileInputStream(filepath);
        this.model.read(in, null, "TTL");

        this.populateGameResourceMaps();
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

                // SETTING FIRST SLIDE ----------------------
                if (slideNode.getProperty(TGEO.categorizedAs) != null){
                    slide.setAsFirstSlide(true);
                }

                this.slideNodeMap.put(slideNode, slide);
            }
        }

        // Populate decisionNodeMap
        ResIterator decisionIter = this.model.listResourcesWithProperty(RDF.type, TGEO.Decision);

        // For each decision, build a Decision instance
        if (decisionIter.hasNext()) {
            while (decisionIter.hasNext()) {
                Resource decisionNode = decisionIter.nextResource();

                String decisionText = decisionNode.getProperty(TGEO.hasText).getString();
                Resource originSlideNode = this.model.listResourcesWithProperty(TGEO.hasDecision, decisionNode).nextResource();
                Slide originSlide = slideNodeMap.get(originSlideNode);
                Resource targetSlideNode = decisionNode.getPropertyResourceValue(TGEO.directsTo);
                Slide targetSlide = slideNodeMap.get(targetSlideNode);
                Decision decision = new Decision(decisionText, originSlide, (int) (Math.random() * 100000), targetSlide);

                if (decisionNode.getProperty(TGEO.givesItem) != null) {
                    decision.itemToGive = decisionNode.getProperty(TGEO.givesItem).getString();
                }



                //listofdecisionconditionals = decisionNode.getProperty(TGEO.requiresDecision)
                //listofitemconditionals = decisionNode.getProperty(TGEO.requiresItem)
                // Loop through the items, add it to decision.addtoitemconditionals
                // loop through the decision pointers, add that to decision.addtodecisionconditionals

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


            Resource itemListNode = decisionNode.getPropertyResourceValue(TGEO.requiresItemList);
            if (itemListNode != null) {
                NodeIterator itemIterator = this.model.listObjectsOfProperty(itemListNode, TGEO.hasItem);

                while (itemIterator.hasNext()) {
                    String item = itemIterator.nextNode().toString();

                    decision.addToItemConditionals(item);
                }
            }
            Resource decisionListNode = decisionNode.getPropertyResourceValue(TGEO.requiresDecisionList);
            if (decisionListNode != null) {
                NodeIterator decisionIterator = this.model.listObjectsOfProperty(decisionListNode, TGEO.hasItem);

                while (decisionIterator.hasNext()) {
                    Resource decisionResource = decisionIterator.nextNode().asResource();
                    Decision d = decisionNodeMap.get(decisionResource);
                    decision.addToDecisionConditionals(d);
                }
            }
        }
    }

    /**
     * Gets all decisions belonging to a particular slide and returns it in a list
     *
     * @param slideNode - The URI of the slide containing decisions from the model
     * @return - A list of the decisions belonging to the above slide
     */
    private ArrayList<Decision> getDecisionsOfSlide(Resource slideNode) {
        NodeIterator iter = this.model.listObjectsOfProperty(slideNode, TGEO.hasDecision);
        ArrayList<RDFNode> results = (ArrayList<RDFNode>) iter.toList();

        ArrayList<Decision> decisions = new ArrayList<>();
        for (RDFNode decisionNode : results) {

            if (decisionNode instanceof Resource) {
                decisions.add(this.decisionNodeMap.get(decisionNode));
            }
        }

        return decisions;
    }
}
