package entities;


import client.GuiSlideExperiment;
import client.GuiDecisionExperiment;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditorGame {
    private final MapProperty<Slide, GuiSlideExperiment> slideMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Decision, GuiDecisionExperiment> decisionMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Slide, GuiSlideExperiment> deletedSlideMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Decision, GuiDecisionExperiment> deletedDecisionMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));

    public ObservableMap<Slide, GuiSlideExperiment> getSlideMap() {
        return this.slideMap.get();
    }

    public ObservableMap<Decision, GuiDecisionExperiment> getDecisionMap() {
        return this.decisionMap.get();
    }

    public MapProperty<Slide, GuiSlideExperiment> slideMapProperty() {
        return this.slideMap;
    }

    public MapProperty<Decision, GuiDecisionExperiment> decisionMapProperty() {return this.decisionMap; }

    public MapProperty<Slide, GuiSlideExperiment> deletedSlideMapProperty() {return this.deletedSlideMap; }

    public MapProperty<Decision, GuiDecisionExperiment> deletedDecisionMapProperty() {return this.deletedDecisionMap; }


    public void connectSlideAndRenderableSlide(Slide s, GuiSlideExperiment rs) {
        this.slideMap.put(s, rs);

        System.out.println(slideMap.values().toString());
    }
    public ArrayList<GuiSlideExperiment> getAllRenderableSlides() {
        return new ArrayList<>(this.slideMap.values());
    }

    public ArrayList<Map.Entry<Slide, GuiSlideExperiment>> getAllEntriesSlide() {
        return new ArrayList<>(this.slideMap.entrySet());
    }

    public ArrayList<Map.Entry<Slide, GuiSlideExperiment>> getAllEntriesDeletedSlide() {
        return new ArrayList<>(this.slideMap.entrySet());
    }

    public ArrayList<Map.Entry<Decision, GuiDecisionExperiment>> getAllEntriesDecision(){
        return new ArrayList<>(this.decisionMap.entrySet());
    }

    //
    public ArrayList<GuiDecisionExperiment> getAllRenderableDecisions() {
        return (ArrayList<GuiDecisionExperiment>) decisionMap.values();
    }


    public void changeDecisionOrigin(Slide slide, GuiSlideExperiment GuiSlide, int DecisionID){
        for (Map.Entry<Decision, GuiDecisionExperiment> decision: decisionMap.entrySet()){
            if (DecisionID == decision.getKey().getId()){

                decision.getKey().setOrigin(slide);
                decision.getValue().originSlide = GuiSlide;
                decision.getValue().originSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateLeftLineX());
                decision.getValue().originSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateLeftLineY());
                deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlideExperiment>) slideRemoved -> {
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

    public void changeDecisionOutput(Slide slide, GuiSlideExperiment GuiSlide, int DecisionID){
        for (Map.Entry<Decision, GuiDecisionExperiment> decision: decisionMap.entrySet()){
            if (DecisionID == decision.getKey().getId()){

                decision.getKey().setTarget(slide);
                decision.getValue().targetSlide = GuiSlide;
                decision.getValue().targetSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateRightLineX());
                decision.getValue().targetSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> decision.getValue().recalculateRightLineY());
                deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlideExperiment>) slideRemoved -> {
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

    public void deleteSlide(Slide slide, GuiSlideExperiment GuiSlide){
        this.deletedSlideMap.put(slide, GuiSlide);
        this.slideMap.remove(slide, GuiSlide);
    }

    public void deleteDecision(Decision decision, GuiDecisionExperiment GuiDecisionExperiment){
        this.deletedDecisionMap.put(decision, GuiDecisionExperiment);
        GuiDecisionExperiment.rightLine.setVisible(false);
        GuiDecisionExperiment.leftLine.setVisible(false);
        this.decisionMap.remove(decision, GuiDecisionExperiment);
        for (Map.Entry<Slide, GuiSlideExperiment> slide: slideMap.entrySet()){
            if (slide.getKey().outgoingDecisions.contains(decision)){
                slide.getKey().outgoingDecisions.remove(decision);
            }
        }

    }

    public void connectDecisionAndRenderableDecision(Decision d, GuiDecisionExperiment r){
        this.decisionMap.put(d, r);


    }



}
