package client.DisplayGame;
import client.MainTitleScreen;
import client.PlayDisplayer;
import client.ThemeColours;
import entities.*;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import rdf.RDFLoadToPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Long.MAX_VALUE;

public class TitleScreen extends GameRenderer{

    GameRenderer gameRenderer;
    ThemeColours theme;
    /**
     * Creates the menu bar at the top of the window.
     *
     */
    TitleScreen(ThemeColours theme, GameRenderer gameRenderer) {
        this.theme = theme;
        this.gameRenderer = gameRenderer;
    }

    public void displayFirstSlide(){
        VBox box = new VBox(400);
        box.setAlignment(Pos.TOP_CENTER);
        Label title =  new Label("Welcome, Player!");
        title.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 55));
        title.setAlignment(Pos.TOP_CENTER);

        Button settings = createSettingsButton();
        Button start = createStartButton();
        Button quit = createQuitButton();

        HBox hBox = new HBox(25);
        hBox.getChildren().addAll(start, settings, quit);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        List<Node> nodes = Arrays.asList(quit, settings,start, title);
        Duration duration = new Duration(2000);
        addFadeAnimation(nodes, duration);
        box.getChildren().addAll(title, hBox);

        Scene scene=new Scene(box,1200,800);
        addMoveAnimation(title, scene, duration);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap");
        stage.setScene(scene);
        stage.show();

        box.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        title.setStyle("-fx-text-fill: " + theme.active.textColour);
    }

    public void reDisplay(){
        stage.close();
        displayFirstSlide();
    }

    public Button createSettingsButton(){
        Button button = createButton();
        SettingsMenu menu =  new SettingsMenu(theme, this);
        menu.initializeSettings();
        button.setOnMouseClicked(event -> menu.displaySettings());
        button.setText("Settings");
        return button;
    }

    private Button createStartButton(){
        Button button = createButton();
        button.setText("Load Game");
        VBox box = new VBox(400);
        Scene window = new Scene(box,1200,800);

        button.setOnMouseClicked(event -> {
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
                    Player player = new Player(gameRenderer, loadedGame);
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
        button.setStyle("-fx-background-color: " + theme.active.slideColour + ";" +
                "-fx-text-fill: " + theme.active.textColour +";");
        addListeners(button);
        return button;
    }


    public void addListeners(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button.setStyle("-fx-background-color:" + theme.active.textColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.slideColour));

        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button.setStyle("-fx-background-color:" + theme.active.slideColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.textColour));
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
