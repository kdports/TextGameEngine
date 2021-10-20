package client;

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

public class GUIBoxScene extends Application
{
    // Creates the class's variables. Notable is idControl, used to create an easy way for created objects to be called
    // Pane is a javafx class
    int idControl = 0;
    Pane root = new Pane();
    private double mouseAnchorX;
    private double mouseAnchorY;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Pane root = new Pane();
        // holds all GUI objects created
        StackPane holder = new StackPane();
        // Creates the GUI's background
        Canvas canvas = new Canvas(5000,  5000);
        // Adds the canvas to the holder and root's children for javafx's use
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        //min dimensions of the window
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(500);

        // Clicking this button creates a new slide in the GUI
        Button btnSlide = this.slideButton("Add Slide");
        root.getChildren().add(btnSlide);

        // This button will call gamerenderer to load the current game
        // TODO: Implement in class once RDFSave is done
        Button btnPlaytest = this.playtestButton("Playtest");
        root.getChildren().add(btnPlaytest);

        // This button will save the game in an RDF file
        // TODO: Implement in class once RDFSave is done
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
        // Sets the details of the slide button
        btnAddSlide.setText(name);
        btnAddSlide.setId("Button " + idControl);
        btnAddSlide.setLayoutX(10);
        btnAddSlide.setLayoutY(10);
        btnAddSlide.setPrefHeight(90);
        btnAddSlide.setPrefWidth(70);
        //DECIDES BUTTON COLOUR
        btnAddSlide.setStyle("-fx-background-color: #c72c41; ");
        // When it's clicked on the created class will have its handle method called
        btnAddSlide.setOnAction(new SlideHandler(idControl, root));
        return btnAddSlide;
    }

    public Button playtestButton(String name) {
        Button btnPlaytest = new Button(name);
        // Sets the details of the button
        btnPlaytest.setStyle("-fx-background-color: #c72c41;");
        btnPlaytest.setLayoutX(10);
        btnPlaytest.setLayoutY(112.5);
        btnPlaytest.setPrefWidth(70);
        btnPlaytest.setPrefHeight(90);
        // When it's clicked on the created class will have its handle method called
        btnPlaytest.setOnAction(new TestHandler());
        return btnPlaytest;
    }

    public Button saveButton(String name) {
        Button btnSave = new Button(name);
        // Sets the details of the button
        btnSave.setStyle("-fx-background-color: #c72c41;");
        btnSave.setLayoutX(10);
        btnSave.setLayoutY(215);
        btnSave.setPrefWidth(70);
        btnSave.setPrefHeight(90);
        // When it's clicked on the created class will have its handle method called
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
