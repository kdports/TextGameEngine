package client;

import entities.EditorGame;
import entities.Slide;
import handlers.Handlers;
import interfaces.RenderableSlide;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.MapChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

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
        // Build all slides
        ArrayList<StackPane> stuffToRender = (
            this.editorGame
                .getAllEntriesSlide()
                .stream()
                .map(GuiSlideExperiment::new)
                .collect(Collectors.toCollection(ArrayList::new))
        );

        // Build all decisions
        stuffToRender.addAll(
                this.editorGame
                    .getAllEntriesDecision()
                    .stream()
                    .map(GuiDecisionExperiment::new)
                    .collect(Collectors.toCollection(ArrayList::new))
        );

        this.root.getChildren().setAll(stuffToRender);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(5000,  5000);
        holder.getChildren().add(canvas);
        this.root.getChildren().add(holder);

        // Set root to observe the this.editorGame hashmaps and update accordingly
        this.editorGame.slideMapProperty().addListener((MapChangeListener<? super Slide, ? super RenderableSlide>) listener -> {
            if (listener.wasAdded()) {
                this.root.getChildren().add(/*   */);
            }

            if (listener.wasRemoved()) {
                // this.root.getChildren().remove()
                // remove child
            }
        });
        this.editorGame.decisionMapProperty().addListener((InvalidationListener) this::rebuildFromRoot);

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
