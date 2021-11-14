package entities;


import client.GuiSlide;
import client.GuiDecision;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditorGame {
    private final MapProperty<Slide, GuiSlide> slideMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Decision, GuiDecision> decisionMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Slide, GuiSlide> deletedSlideMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Decision, GuiDecision> deletedDecisionMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));

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


    public void connectSlideAndRenderableSlide(Slide s, GuiSlide rs) {
        this.slideMap.put(s, rs);

        System.out.println(slideMap.values().toString());
    }
    public ArrayList<GuiSlide> getAllRenderableSlides() {
        return new ArrayList<>(this.slideMap.values());
    }

    public ArrayList<Map.Entry<Slide, GuiSlide>> getAllEntriesSlide() {
        return new ArrayList<>(this.slideMap.entrySet());
    }

    public ArrayList<Map.Entry<Slide, GuiSlide>> getAllEntriesDeletedSlide() {
        return new ArrayList<>(this.slideMap.entrySet());
    }

    public ArrayList<Map.Entry<Decision, GuiDecision>> getAllEntriesDecision(){
        return new ArrayList<>(this.decisionMap.entrySet());
    }

    //
    public ArrayList<GuiDecision> getAllRenderableDecisions() {
        return (ArrayList<GuiDecision>) decisionMap.values();
    }


    public void changeDecisionOrigin(Slide slide, GuiSlide GuiSlide, int DecisionID){
        for (Map.Entry<Decision, GuiDecision> decision: decisionMap.entrySet()){
            if (DecisionID == decision.getKey().getId()){
                // Remove changing decision from old slide
                decision.getKey().getOrigin().outgoingDecisions.remove(decision.getKey());

                // Add decision to the new slides outgoing Decision
                slide.outgoingDecisions.add(decision.getKey());

                // Change decision origin and make necessary adjustment
                decision.getKey().setOrigin(slide);
                decision.getValue().originSlide = GuiSlide;
                decision.getValue().originSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateLeftLineX());
                decision.getValue().originSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateLeftLineY());
                deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) slideRemoved -> {
                    if (slideRemoved.getValueAdded() == decision.getValue().originSlide) {
                        decision.getValue().originSlide = null;
                        decision.getValue().recalculateLeftLineX();
                    }}
                );
                decision.getValue().recalculateLeftLineX();
                decision.getValue().recalculateLeftLineY();
            }
        }
    }

    public void changeDecisionOutput(Slide slide, GuiSlide GuiSlide, int DecisionID){
        for (Map.Entry<Decision, GuiDecision> decision: decisionMap.entrySet()){
            if (DecisionID == decision.getKey().getId()){

                decision.getKey().setTarget(slide);
                decision.getValue().targetSlide = GuiSlide;
                decision.getValue().targetSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateRightLineX());
                decision.getValue().targetSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateRightLineY());
                deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) slideRemoved -> {
                    if (slideRemoved.getValueAdded() == decision.getValue().targetSlide) {
                        decision.getValue().targetSlide = null;
                        decision.getValue().recalculateRightLineX();
                    }}
                );
                decision.getValue().recalculateRightLineX();
                decision.getValue().recalculateRightLineY();
            }
        }
    }

    public void deleteSlide(Slide slide, GuiSlide GuiSlide){
        this.deletedSlideMap.put(slide, GuiSlide);
        this.slideMap.remove(slide, GuiSlide);
    }

    public void deleteDecision(Decision decision, GuiDecision GuiDecision){
        this.deletedDecisionMap.put(decision, GuiDecision);
        this.decisionMap.remove(decision, GuiDecision);
        GuiDecision.rightLine.setVisible(false);
        GuiDecision.leftLine.setVisible(false);
        for (Map.Entry<Slide, GuiSlide> slide: slideMap.entrySet()){
            if (slide.getKey().outgoingDecisions.contains(decision)){
                slide.getKey().outgoingDecisions.remove(decision);
            }
        }

    }

    public void connectDecisionAndRenderableDecision(Decision d, GuiDecision r){
        this.decisionMap.put(d, r);


    }

    public void clearAll(){
        for (Map.Entry<Decision, GuiDecision> entry : this.getAllEntriesDecision()){
            this.deleteDecision(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Slide, GuiSlide> entry : this.getAllEntriesSlide()){
            this.deleteSlide(entry.getKey(), entry.getValue());
        }
    }


}
