package client;

import interfaces.EditDisplayer;
import entities.Studio;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class RootDisplayer extends Application implements EditDisplayer {
    int idControl = 0;
    Pane root = new Pane();
    private double mouseAnchorX;
    private double mouseAnchorY;
    private Studio studio;
    private SaveHandler saveHandler;

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public void start(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(5000,  5000);
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        //min dimensions of the window
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(500);

        this.addButtons(root);

        Scene window = new Scene(root, 1920, 1080);
        //DECIDES BACKGROUND COLOUR
        holder.setStyle("-fx-background-color: #2d142c");
        primaryStage.setTitle("Text Studio");
        primaryStage.setScene(window);
        primaryStage.show();
    }

    /**
     * Add all the main buttons (Add Slide, Play Test, and Save) to the GUI
     * @param root - The root of the tree being rendered
     */
    private void addButtons(Pane root) {
        Button createNewSlideButton = this.addCreateNewSlideButton();
        root.getChildren().add(createNewSlideButton);

        Button playTestButton = this.addPlayTestButton();
        root.getChildren().add(playTestButton);

        Button saveButton = this.addSaveButton();
        root.getChildren().add(saveButton);
    }

    public Button addCreateNewSlideButton() {
        Button btnAddSlide = new Button();
        btnAddSlide.setText("Add Slide");
        btnAddSlide.setId("Button " + idControl);
        btnAddSlide.setLayoutX(10);
        btnAddSlide.setLayoutY(10);
        btnAddSlide.setPrefHeight(90);
        btnAddSlide.setPrefWidth(70);

        //DECIDES BUTTON COLOUR
        btnAddSlide.setStyle("-fx-background-color: #c72c41; ");
        btnAddSlide.setOnAction(new CreateNewSlideHandler(this, this.studio));

        return btnAddSlide;
    }

    public Button addPlayTestButton() {
        Button btnPlaytest = new Button("Play Test");
        btnPlaytest.setStyle("-fx-background-color: #c72c41;");
        btnPlaytest.setLayoutX(10);
        btnPlaytest.setLayoutY(112.5);
        btnPlaytest.setPrefWidth(70);
        btnPlaytest.setPrefHeight(90);
        btnPlaytest.setOnAction(new TestHandler());
        return btnPlaytest;
    }

    public Button addSaveButton() {
        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: #c72c41;");
        btnSave.setLayoutX(10);
        btnSave.setLayoutY(215);
        btnSave.setPrefWidth(70);
        btnSave.setPrefHeight(90);
        btnSave.setOnAction(new SaveHandler());
        return btnSave;
    }

    @Override
    public void createSlide() {
        idControl++;
        System.out.println("wow");
        GUIScene GuiScene = new GUIScene("Scene", idControl, root);

        GuiScene.setLayoutX(90);
        GuiScene.setLayoutY(140 + 50 * (root.getChildren().size() % 10));
        root.getChildren().add(GuiScene);
    }

    @Override
    public void createDecision() {
        // There needs to be code in here
    }
}
