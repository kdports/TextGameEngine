package main.java.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.util.Objects;

public class GUIBoxScene extends Application
{
    int idControl = 0;
    private double mouseAnchorX;
    private double mouseAnchorY;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = new Pane();
        Button btnAddSlide = new Button();
        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(5000,  5000);
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        //min dimensions of the window
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(500);

        btnAddSlide.setText("Add Slide");
        btnAddSlide.setId("Button " + idControl);
        btnAddSlide.setLayoutX(10);
        btnAddSlide.setLayoutY(10);
        btnAddSlide.setPrefHeight(90);
        //DECIDES BUTTON COLOUR
        btnAddSlide.setStyle("-fx-background-color: #c72c41; ");
        btnAddSlide.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                idControl++;
                GUIScene GuiScene = new GUIScene("Scene", idControl, root);
                GuiScene.setLayoutX(90);
                GuiScene.setLayoutY(140 + 50 * (root.getChildren().size() % 10));
                root.getChildren().add(GuiScene);
            }
        });
        Button btnPlaytest = new Button("Playtest");
        btnPlaytest.setStyle("-fx-background-color: #c72c41;");
        btnPlaytest.setLayoutX(10);
        btnPlaytest.setLayoutY(112.5);
        btnPlaytest.setPrefWidth(70);
        btnPlaytest.setPrefHeight(90);
        btnPlaytest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //calls playgame
            }
        });

        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: #c72c41;");
        btnSave.setLayoutX(10);
        btnSave.setLayoutY(215);
        btnSave.setPrefWidth(70);
        btnSave.setPrefHeight(90);
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //calls rdf save
            }
        });

        root.getChildren().addAll(btnAddSlide, btnPlaytest, btnSave);
        Scene window = new Scene(root, 1920, 1080);
        //DECIDES BACKGROUND COLOUR
        holder.setStyle("-fx-background-color: #2d142c");
        primaryStage.setTitle("Text Studio");
        primaryStage.setScene(window);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
