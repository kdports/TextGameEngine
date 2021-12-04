package client.DisplayGame;
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
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Long.MAX_VALUE;

public class TitleScreen extends GameRenderer{

    public void displayFirstSlide(){
        VBox box = new VBox(400);
        box.setAlignment(Pos.TOP_CENTER);
        Label title =  new Label("Title Screen");
        title.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 55));
        title.setAlignment(Pos.TOP_CENTER);
        Button start = createStartButton();
        Button settings = createSettingsButton();
        Button quit = createQuitButton();
        HBox hBox = new HBox(25);
        hBox.getChildren().addAll(start, settings, quit);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        List<Node> nodes = Arrays.asList(quit, start, settings, title);
        Duration duration = new Duration(2400);
        addFadeAnimation(nodes, duration);
        box.getChildren().addAll(title, hBox);

        Scene scene=new Scene(box,1200,800);
        addMoveAnimation(title, scene, duration);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap");
        stage.setScene(scene);
        stage.show();
    }

    public Button createStartButton(){
        Button button = createButton();
        button.setOnAction(arg0 -> player.playGame());
        button.setText("Start Game");
        return button;
    }

    private Button createSettingsButton(){
        Button button = createButton();
        button.setOnAction(arg0 -> System.out.println("menu")); // Temporary
        button.setText("Settings");
        return button;
    }


    private Button createQuitButton(){
        Button button = createButton();
        button.setOnAction(arg0 -> stage.close());
        button.setText("Quit");
        return button;
    }

    private Button createButton() {
        Button button = new Button();
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 25));
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
