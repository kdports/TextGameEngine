package client;

import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import handlers.Handlers;
import handlers.SlideHandler;
import interfaces.RenderableSlide;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.MapChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RootDisplayerExperiment extends Application {
    private final Pane root = new Pane();
    private final EditorGame editorGame = new EditorGame();
    // This needs to be instantiated here because there is no way to pass editorGame back out until it is too late,
    // But we require the methods that Handlers provides before start() is finished. So here works.
    private final Handlers handlers = new Handlers(editorGame);

    public void begin(String[] args) {
        launch(args);
    }

    public void rebuildFromRoot(Object o) {
        System.out.println("Re-rendering!!!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(5000,  5000);
        holder.getChildren().add(canvas);
        this.root.getChildren().add(holder);

        // Set root to observe the this.editorGame hashmaps and update accordingly
        this.editorGame.slideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlideExperiment>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().add(listener.getValueAdded());
                listener.getKey().returnObservable().addListener(
                        (observable, oldvalue, newvalue) -> listener.getValueAdded().prompt.setText(newvalue)
                );
            }
        });


        // Set root to observe and delete slides properly
        this.editorGame.deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlideExperiment>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().remove(listener.getValueAdded());
            }
        });


        // Set root to initialize connecting lines and decisions
        this.editorGame.decisionMapProperty().addListener((MapChangeListener<? super Decision, ? super GuiDecisionExperiment>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().add(listener.getValueAdded());
               listener.getValueAdded().originSlide.layoutXProperty().addListener(
                       (observable, oldvalue, newvalue) -> listener.getValueAdded().recalculateLeftLineX());
               listener.getValueAdded().originSlide.layoutYProperty().addListener(
                       (observable, oldvalue, newvalue) -> listener.getValueAdded().recalculateLeftLineY());
               root.getChildren().add(listener.getValueAdded().leftLine);
               root.getChildren().add(listener.getValueAdded().rightLine);

            }

        });

        this.editorGame.deletedDecisionMapProperty().addListener((MapChangeListener<? super Decision, ? super GuiDecisionExperiment>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().remove(listener.getValueAdded());
            }
        });

        // Add the three sidebar buttons
        SidebarButtons sidebarButtons = new SidebarButtons();
        this.root.getChildren().addAll(sidebarButtons);

        Scene window = new Scene(this.root, 1920, 1080);
        holder.setStyle("-fx-background-color: #FFFFFF");
        primaryStage.setTitle("Text Studio");
        primaryStage.setScene(window);
        primaryStage.show();
    }
}
