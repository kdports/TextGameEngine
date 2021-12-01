package entities;

import client.GuiSlide.GuiSlide;
import client.GuiDecision.GuiDecision;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The object in which all data about the Editor is stored. All slides,
 * decisions, and attributes of slides and decisions are stored here in
 * hashmaps
 */
public class EditorGame {
    public Slide firstSlide;
    private final MapProperty<Slide, GuiSlide> slideMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Decision, GuiDecision> decisionMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Slide, GuiSlide> deletedSlideMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Decision, GuiDecision> deletedDecisionMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));

    /**
     * For each method below, gets and returns the map as named in the method name.
     */
    public ObservableMap<Slide, GuiSlide> getSlideMap() {
        return this.slideMap.get();
    }
    public ObservableMap<Decision, GuiDecision> getDecisionMap() {
        return this.decisionMap.get();
    }
    public MapProperty<Slide, GuiSlide> slideMapProperty() {
        return this.slideMap;
    }
    public MapProperty<Decision, GuiDecision> decisionMapProperty() {return this.decisionMap; }
    public MapProperty<Slide, GuiSlide> deletedSlideMapProperty() {return this.deletedSlideMap; }
    public MapProperty<Decision, GuiDecision> deletedDecisionMapProperty() {return this.deletedDecisionMap; }


    /**
     * Connect a single Slide to a single GuiSlide. The GuiSlide is thought to be "representing" the Slide in the GUI.
     *
     * @param slide - The slide to become the key of the connection.
     * @param guiSlide - The GUI version to become the value of the connection.
     */
    public void connectSlideAndRenderableSlide(Slide slide, GuiSlide guiSlide) {
        this.slideMap.put(slide, guiSlide);
    }

    /**
     * Connect a single Decision to a single GuiDecision. The GuiDecision is thought to be "representing" the Decision
     * in the GUI.
     *
     * @param decision - The slide to become the key of the connection.
     * @param guiDecision - The GUI version to become the value of the connection.
     */
    public void connectDecisionAndRenderableDecision(Decision decision, GuiDecision guiDecision) {
        this.decisionMap.put(decision, guiDecision);
    }

    /**
     * Gets every slide entity from the EditorGame as an arraylist
     *
     * @return - An arraylist of slides
     */
    public ArrayList<Map.Entry<Slide, GuiSlide>> getAllEntriesSlide() {
        return new ArrayList<>(this.slideMap.entrySet());
    }

    /**
     * Gets every decision entity from the EditorGame as an arraylist
     *
     * @return - An arraylist of decisions
     */
    public ArrayList<Map.Entry<Decision, GuiDecision>> getAllEntriesDecision(){
        return new ArrayList<>(this.decisionMap.entrySet());
    }

    /**
     * Set a given slide as the first slide.
     *
     * @param slide - The slide to become the first slide.
     */
    public void setFirstSlide(Slide slide){
        if (firstSlide != null) {
            firstSlide.setAsFirstSlide(false);
        }
        firstSlide = slide;
        slide.setAsFirstSlide(true);
    }

    /**
     * On the GUI level, change the Slide that the decision COMES from.
     *
     * @param slide - The slide that will be the new origin.
     * @param guiSlide - The GuiSlide that corresponds to that new origin.
     * @param decisionId - The ID of the decision that is changing origin.
     */
    public void changeDecisionOrigin(Slide slide, GuiSlide guiSlide, int decisionId){
        for (Map.Entry<Decision, GuiDecision> entry : decisionMap.entrySet()){
            Decision decision = entry.getKey();
            GuiDecision guiDecision = entry.getValue();

            if (decisionId == decision.getId()) {
                // Remove changing decision from old slide
                decision.getOrigin().outgoingDecisions.remove(decision);

                // Add decision to the new slides outgoing Decision
                slide.outgoingDecisions.add(decision);

                // Change decision origin and make necessary adjustment
                decision.setOrigin(slide);
                guiDecision.leftLine.setSlide(guiSlide);
                guiDecision.originSlide = guiSlide;

                guiDecision.originSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> guiDecision.leftLine.recalculateX());
                guiDecision.originSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> guiDecision.leftLine.recalculateY());
                deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) slideRemoved -> {
                    if (slideRemoved.getValueAdded() == guiDecision.originSlide) {
                        guiDecision.originSlide = null;
                        // This is a big boy hack to get it to stop displaying. We should change this into its own destroy() or hide() method.
                        guiDecision.leftLine.recalculateX();
                    }}
                );
                guiDecision.leftLine.recalculateX();
                guiDecision.leftLine.recalculateY();
            }
        }
    }

    /**
     * On the GUI level, change the TARGET of a single Decision.
     *
     * @param slide - The new TARGET slide.
     * @param guiSlide - The GUI version of the new TARGET slide.
     * @param decisionId - The ID of the decision that is being changed.
     */
    public void changeDecisionTarget(Slide slide, GuiSlide guiSlide, int decisionId){
        for (Map.Entry<Decision, GuiDecision> entry : decisionMap.entrySet()) {
            Decision decision = entry.getKey();
            GuiDecision guiDecision = entry.getValue();

            if (decisionId == decision.getId()) {
                decision.setTarget(slide);
                guiDecision.targetSlide = guiSlide;
                guiDecision.rightLine.setSlide(guiSlide);

                guiDecision.targetSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> guiDecision.rightLine.recalculateX());
                guiDecision.targetSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> guiDecision.rightLine.recalculateY());
                deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) slideRemoved -> {
                    if (slideRemoved.getValueAdded() == guiDecision.targetSlide) {
                        guiDecision.targetSlide = null;
                        // This is a big boy hack to get it to stop displaying. We should change this into its own destroy() or hide() method.
                        guiDecision.rightLine.recalculateX();
                    }}
                );

                guiDecision.rightLine.recalculateX();
                guiDecision.rightLine.recalculateY();
            }
        }
    }

    /**
     * Delete a single slide from the studio. This alters the slideMap and deletedSlideMap.
     *
     * @param slide - The slide to delete.
     */
    public void deleteSlide(Slide slide) {
        if (firstSlide == slide){
            firstSlide = null;
        }
        this.deletedSlideMap.put(slide, this.slideMap.get(slide));
        this.slideMap.remove(slide);
    }

    /**
     * Delete a single decision. Adds to deletedDecisionMaps.
     *
     * @param decision - The decision to delete.
     */
    public void deleteDecision(Decision decision) {
        GuiDecision guiDecision = this.decisionMap.get(decision);

        this.deletedDecisionMap.put(decision, guiDecision);
        this.decisionMap.remove(decision);
        guiDecision.rightLine.setVisible(false);
        guiDecision.leftLine.setVisible(false);

        for (Map.Entry<Slide, GuiSlide> slide: slideMap.entrySet()){
            // No need to check for contains() because remove() exits if it doesn't exist.
            slide.getKey().outgoingDecisions.remove(decision);
        }
    }

    public void addDecisionConditional(Decision decision, Decision toAdd) {

    }

    /**
     * Clear all the data out of the editorGame instance.
     */
    public void clearAll(){

        for (Map.Entry<Slide, GuiSlide> entry : this.getAllEntriesSlide()){
            this.deleteSlide(entry.getKey());
        }

        for (Map.Entry<Decision, GuiDecision> entry : this.getAllEntriesDecision()){
            entry.getValue().rightLine.deleteLine();
            entry.getValue().leftLine.deleteLine();
            this.deleteDecision(entry.getKey());

        }
    }
}
