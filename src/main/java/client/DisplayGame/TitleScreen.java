package client.DisplayGame;
import client.GuiDecision.GuiDecision;
import client.GuiSlide.GuiSlide;
import client.Main;
import client.MainTitleScreen;
import client.PlayDisplayer;
import client.RootDisplayer;
import entities.*;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import rdf.RDFLoadToPlayer;
import rdf.RDFLoadToStudio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.Long.MAX_VALUE;

public class TitleScreen extends GameRenderer{

    public void displayFirstSlide(){
        VBox box = new VBox(400);
        box.setAlignment(Pos.TOP_CENTER);
        Label title =  new Label("Welcome, Player!");
        title.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 55));
        title.setAlignment(Pos.TOP_CENTER);

        Button settings = createStartButton();
        Button start = createSettingsButton();
        Button quit = createQuitButton();

        HBox hBox = new HBox(25);
        hBox.getChildren().addAll(start, settings, quit);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        List<Node> nodes = Arrays.asList(quit, settings,start, title);
        Duration duration = new Duration(2400);
        addFadeAnimation(nodes, duration);
        box.getChildren().addAll(title, hBox);

        Scene scene=new Scene(box,1200,800);
        addMoveAnimation(title, scene, duration);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap");
        stage.setScene(scene);
        stage.show();

        box.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        title.setStyle("-fx-text-fill: " + theme.active.textColour);
        start.setStyle("-fx-background-color: " + theme.active.slideColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
        settings.setStyle("-fx-background-color: " + theme.active.slideColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
        quit.setStyle("-fx-background-color: " + theme.active.slideColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
    }

    public Button createStartButton(){
        Button button = createButton();
        //button.setOnAction(arg0 -> player.playGame());
        button.setText("Settings");
        return button;
    }

    private Button createSettingsButton(){
        Button button = createButton();
        button.setText("Load Game");
        VBox box = new VBox(400);
        Scene window = new Scene(box,1200,800);

        button.setOnMouseClicked(event -> {
            PlayDisplayer playDisplayer = new GameRenderer();


            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("Turtle File (.ttl)", "*.ttl");
            fileChooser.getExtensionFilters().add(exFilter);
            File location = fileChooser.showOpenDialog(window.getWindow());

            // If the user chose a valid file to load from, load it in.
            if (location != null){
                String path = location.getAbsolutePath();
                try {
                    RDFLoadToPlayer loader = new RDFLoadToPlayer(path);
                    Game loadedGame = loader.loadGameFromFile();
                    Player player = new Player(playDisplayer, loadedGame);
                    player.playGame();

                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        return button;
    }

    private Button createQuitButton(){
        Button button = createButton();
        button.setOnAction(arg0 -> {
            stage.close();
            MainTitleScreen back = new MainTitleScreen();
            back.start(new Stage());
        });
        button.setText("Back");
        return button;
    }

    private Button createButton() {
        Button button = new Button();
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 15));
        button.setMaxWidth(MAX_VALUE);
        button.setPrefSize(180, 80);
        button.setCursor(Cursor.HAND);
        button.setWrapText(true);
        button.setStyle("-fx-background-color: #ADD8E6; -fx-border-width: 2px;" + "-fx-text-fill: " + theme.active.textColour);
        return button;
    }

    private void addButtonAnimation(Button button, Color color){

    }

    private void addFadeAnimation(List<Node> nodes, Duration duration){
        for (Node node : nodes) {
            FadeTransition ft = new FadeTransition(duration, node);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }

    private void addMoveAnimation(Node title, Scene scene, Duration duration){
        double sceneWidth = scene.getHeight();
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(title);
        translate.setDuration(duration);
        translate.setByY(sceneWidth / 2.8);
        translate.play();
    }

}
