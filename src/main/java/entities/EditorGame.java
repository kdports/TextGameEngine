package entities;

import interfaces.RenderableDecision;
import interfaces.RenderableSlide;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditorGame {
    private final MapProperty<Slide, RenderableSlide> slideMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));
    private final MapProperty<Decision, RenderableDecision> decisionMap = new SimpleMapProperty<>(FXCollections.observableMap(new HashMap<>()));

    public ObservableMap<Slide, RenderableSlide> getSlideMap() {
        return this.slideMap.get();
    }

    public MapProperty<Slide, RenderableSlide> slideMapProperty() {
        return this.slideMap;
    }

    public MapProperty<Decision, RenderableDecision> decisionMapProperty() {return this.decisionMap; }


    public void connectSlideAndRenderableSlide(Slide s, RenderableSlide rs) {
        this.slideMap.put(s, rs);
        System.out.println(slideMap.values().toString());
    }

    // public static void connectSlideToRenderableSlide(Slide s, RenderableSlide r) {
    //     slideMap.put(s, r);
    // }
    //
    // public static void connectDecisionToRenderableDecision(Decision d, RenderableDecision r) {
    //     decisionMap.put(d, r);
    // }
    //
    public ArrayList<RenderableSlide> getAllRenderableSlides() {
        return new ArrayList<>(this.slideMap.values());
    }
    public ArrayList<Map.Entry<Slide, RenderableSlide>> getAllEntriesSlide() {
        return new ArrayList<>(this.slideMap.entrySet());
    }
    public ArrayList<Map.Entry<Slide, RenderableSlide>> getAllEntriesDeletedSlide() {
        return new ArrayList<>(this.slideMap.entrySet());
    }
    public ArrayList<Map.Entry<Decision, RenderableDecision>>getAllEntriesDecision(){
        return new ArrayList<>(this.decisionMap.entrySet());
    }

    //
    public ArrayList<RenderableDecision> getAllRenderableDecisions() {
        return (ArrayList<RenderableDecision>) decisionMap.values();
    }
    //
    // public static RenderableSlide getRenderableSlide(Slide s) {
    //     return slideMap.get(s);
    // }
    //
    // public static RenderableDecision getRenderableDecision(Decision d) {
    //     return decisionMap.get(d);
    // }

    public void deleteSlide(Map.Entry<Slide, RenderableSlide> entry){
        this.slideMap.remove(entry.getKey(), entry.getValue());
    }
    public void connectDecisionAndRenderableDecision(Decision d, RenderableDecision r){
        this.decisionMap.put(d, r);
    }



}
