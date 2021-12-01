package client;

import buttons.SidebarButtons;
import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import entities.Decision;
import entities.EditorGame;
import entities.Slide;
import handlers.Handlers;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.MapChangeListener;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import rdf.RDFLoadToStudio;
import javafx.scene.control.ScrollBar;

import java.util.Map;

import static java.lang.Math.abs;

public class RootDisplayer extends Application {
    public Scene mainScene; // For testing since I can't figure out how to access the stage otherwise
    private final Pane root = new Pane();
    private final EditorGame editorGame = new EditorGame();
    // This needs to be instantiated here because there is no way to pass editorGame back out until it is too late,
    // But we require the methods that Handlers provides before start() is finished. So here works.
    private final Handlers handlers = new Handlers(editorGame);

    public void begin(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane holder = new StackPane();
        ScrollPane scrollPane = new ScrollPane();
        Canvas canvas = new Canvas(1920,  1080);
        holder.getChildren().add(canvas);
        scrollPane.setContent(this.root);
        scrollPane.setMaxHeight(canvas.getHeight());
        scrollPane.setMaxWidth(canvas.getWidth());

        this.root.getChildren().add(holder);



        Scene window = new Scene(scrollPane, 1920, 1080);
        scrollPane.setVmax((canvas.getHeight() - window.getHeight()));
        scrollPane.setHmax((canvas.getWidth() - window.getWidth()));

        // Add the listeners onto editorGame maps
        this.configureListeners(scrollPane);

        // Add the three sidebar buttons
        ThemeColours theme = new ThemeColours();
        SidebarButtons sidebarButtons = new SidebarButtons(window, this.editorGame, scrollPane, theme);
        this.root.getChildren().addAll(sidebarButtons);



        holder.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        primaryStage.setTitle("Text Studio");
        primaryStage.setScene(window);
        primaryStage.show();
        mainScene = window;
    }

    /**
     * Add listeners onto slideMap, decisionMap, deletedSlideMap, and deletedDecisionMap properties.
     */
    private void configureListeners(ScrollPane scrollPane) {
        // Set root to observe the this.editorGame hashmaps and update accordingly
        this.editorGame.slideMapProperty().addListener((MapChangeListener<? super Slide, ? super GuiSlide>) listener -> {
            if (listener.wasAdded()) {
                this.root.getChildren().add(listener.getValueAdded());
                listener.getKey().getObservablePrompt().addListener(
                        (observable, oldvalue, newvalue) -> listener.getValueAdded().prompt.setText(newvalue)
                );

                scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> {
                    listener.getValueAdded().sceneY = abs(newvalue.getMinY());
                    listener.getValueAdded().sceneX = abs(newvalue.getMinX());
                        }
                );

                listener.getValueAdded().sceneY = abs(scrollPane.getViewportBounds().getMinY());
                listener.getValueAdded().sceneX = abs(scrollPane.getViewportBounds().getMinX());
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

                // Make x and y update according to where it is on stackpane

                scrollPane.viewportBoundsProperty().addListener((observable, oldvalue, newvalue) -> {
                            listener.getValueAdded().sceneY = abs(newvalue.getMinY());
                            listener.getValueAdded().sceneX = abs(newvalue.getMinX());
                        }
                );

                listener.getValueAdded().sceneY = abs(scrollPane.getViewportBounds().getMinY());
                listener.getValueAdded().sceneX = abs(scrollPane.getViewportBounds().getMinX());

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
