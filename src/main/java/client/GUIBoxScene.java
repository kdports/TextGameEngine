package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.util.ArrayList;

public class GUIBoxScene extends Application
{
    int idControl = 0;
    Pane root = new Pane();
    private double mouseAnchorX;
    private double mouseAnchorY;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Pane root = new Pane();
        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(5000,  5000);
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        //min dimensions of the window
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(500);

        Button btnSlide = this.slideButton("Add Slide");
        root.getChildren().add(btnSlide);

        Button btnPlaytest = this.playtestButton("Playtest");
        root.getChildren().add(btnPlaytest);

        Button btnSave = this.saveButton("Save");
        root.getChildren().add(btnSave);

        Scene window = new Scene(root, 1920, 1080);
        //DECIDES BACKGROUND COLOUR
        holder.setStyle("-fx-background-color: #2d142c");
        primaryStage.setTitle("Text Studio");
        primaryStage.setScene(window);
        primaryStage.show();
    }

    public Button slideButton(String name) {
        Button btnAddSlide = new Button();
        btnAddSlide.setText(name);
        btnAddSlide.setId("Button " + idControl);
        btnAddSlide.setLayoutX(10);
        btnAddSlide.setLayoutY(10);
        btnAddSlide.setPrefHeight(90);
        btnAddSlide.setPrefWidth(70);
        //DECIDES BUTTON COLOUR
        btnAddSlide.setStyle("-fx-background-color: #c72c41; ");
        btnAddSlide.setOnAction(new SlideHandler(idControl, root));
        return btnAddSlide;
    }

    public Button playtestButton(String name) {
        Button btnPlaytest = new Button(name);
        btnPlaytest.setStyle("-fx-background-color: #c72c41;");
        btnPlaytest.setLayoutX(10);
        btnPlaytest.setLayoutY(112.5);
        btnPlaytest.setPrefWidth(70);
        btnPlaytest.setPrefHeight(90);
        btnPlaytest.setOnAction(new TestHandler());
        return btnPlaytest;
    }

    public Button saveButton(String name) {
        Button btnSave = new Button(name);
        btnSave.setStyle("-fx-background-color: #c72c41;");
        btnSave.setLayoutX(10);
        btnSave.setLayoutY(215);
        btnSave.setPrefWidth(70);
        btnSave.setPrefHeight(90);
        btnSave.setOnAction(new SaveHandler());
        return btnSave;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
