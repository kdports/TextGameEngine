package client;

import buttons.SidebarButtons;
import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import handlers.Handlers;
import javafx.application.Application;
import javafx.collections.MapChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rdf.RDFLoadToStudio;

import java.util.Map;

/**
 * Creates a window, adds our necessary data to it, then launches the GUI.
 */
public class RootDisplayer extends Application {
    private final Pane root = new Pane();
    private final EditorGame editorGame = new EditorGame();
    // This needs to be instantiated here because there is no way to pass editorGame back out until it is too late,
    // But we require the methods that Handlers provides before start() is finished. So here works.
    private final Handlers handlers = new Handlers(editorGame);

    public void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(5000,  5000);
        holder.getChildren().add(canvas);
        this.root.getChildren().add(holder);

        // Add the listeners onto editorGame maps
        this.configureListeners();

        Scene window = new Scene(this.root, 1920, 1080);
        // Add the three sidebar buttons
        SidebarButtons sidebarButtons = new SidebarButtons(window, this.editorGame);
        this.root.getChildren().addAll(sidebarButtons);

        // Used for setting conditionals
        GuiDecision.editorGame = editorGame;

        holder.setStyle("-fx-background-color: #ff847c");
        primaryStage.setTitle("Text Studio");
        primaryStage.setScene(window);
        primaryStage.show();
    }

    /**
     * Add listeners onto slideMap, decisionMap, deletedSlideMap, and deletedDecisionMap properties.
     */
    private void configureListeners() {
        // Set root to observe the this.editorGame hashmaps and update accordingly
        this.editorGame.slideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().add(listener.getValueAdded());
                listener.getKey().getObservablePrompt().addListener(
                        (observable, oldvalue, newvalue) -> listener.getValueAdded().prompt.setText(newvalue)
                );
            }
        });


        // Set root to observe and delete slides properly
        this.editorGame.deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().remove(listener.getValueAdded());
            }
        });


        // Set root to initialize connecting lines and decisions
        this.editorGame.decisionMapProperty().addListener((MapChangeListener<? super Decision, ? super GuiDecision>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().add(listener.getValueAdded());
                listener.getValueAdded().originSlide.layoutXProperty().addListener(
                        (observable, oldvalue, newvalue) -> listener.getValueAdded().leftLine.recalculateX()
                );
                listener.getValueAdded().originSlide.layoutYProperty().addListener(
                        (observable, oldvalue, newvalue) -> listener.getValueAdded().leftLine.recalculateY()
                );
                this.editorGame.deletedSlideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) slideRemoved -> {
                    if (slideRemoved.getValueAdded() == listener.getValueAdded().originSlide) {
                        listener.getValueAdded().originSlide = null;
                        listener.getValueAdded().leftLine.recalculateX();
                    }}
                );
                root.getChildren().add(listener.getValueAdded().leftLine);
                root.getChildren().add(listener.getValueAdded().rightLine);

            }

        });

        this.editorGame.deletedDecisionMapProperty().addListener((MapChangeListener<? super Decision, ? super GuiDecision>) listener -> {
            if (listener.wasAdded()) {
                System.out.println(listener.getValueAdded().toString());
                this.root.getChildren().remove(listener.getValueAdded());
            }
        });
    }
}
