package client;

import entities.EditorGame;
import handlers.Handlers;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(5000,  5000);
        holder.getChildren().add(canvas);
        this.root.getChildren().add(holder);

        // Set root to observe the this.editorGame hashmaps and update accordingly
        this.editorGame
                .slideMapProperty()
                .addListener((InvalidationListener) nothing -> this.root.getChildren().addAll(
                    this.editorGame
                        .getAllEntries()
                        .stream()
                        .map(GuiSlideExperiment::new)
                        .collect(Collectors.toCollection(ArrayList::new))
                )
        );

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
