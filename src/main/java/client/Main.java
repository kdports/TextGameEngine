package client;

import client.DisplayGame.TitleScreen;
import entities.EditorGame;
import entities.Studio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    protected static Stage stage;
    public Pane root;

    public static void showTitle(){
        BorderPane pane = new BorderPane();

    }

    public static void begin(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        root = new Pane();
        AnchorPane anchor = new AnchorPane();

        stage = primaryStage;
        stage.setTitle("Text Game Engine");

        Button studio = new Button("Launch Studio");
        studio.setLocation(500, 500);

        // IF YOU CLICK LAUNCH PLAYER, LAUNCH GAMERENDERER
        // OTHERWISE LAUNCH STUDIO

        Scene scene = new Scene(root,1200,800);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Main.begin(args);
    }
}