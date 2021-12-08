package client;

import client.DisplayGame.GameRenderer;
import javafx.animation.*;
import javafx.application.Application;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

import static java.lang.Long.MAX_VALUE;

/**
 * The title screen that shows up when you first launch Main.java
 */
public class MainTitleScreen extends Application {

    ThemeColours theme  = new ThemeColours();

    /**
     * Start method required for Application
     * @param primaryStage Stage to use
     */
    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("Text Game Engine");
        displayFirstSlide(primaryStage);
        primaryStage.show();
    }

    /**
     * Display the title screen
     * @param stage Stage to display one
     */
    public void displayFirstSlide(Stage stage){
        VBox box = new VBox(400);
        box.setAlignment(Pos.TOP_CENTER);
        Label title =  new Label("Text Game Engine!");
        title.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 55));
        title.setAlignment(Pos.TOP_CENTER);

        Button settings = createGEButton(stage);
        Button start = createGPButton(stage);
        Button quit = createQuitButton(stage);

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

        box.setStyle("-fx-background-color: " + theme.active.backgroundColour);
        title.setStyle("-fx-text-fill: " + theme.active.textColour);
    }

    /**
     * Creates the button for the game engine
     *
     * @param stage stage to display on
     * @return Button - Button that starts the game engine
     */
    public Button createGEButton(Stage stage){
        Button button = createButton();
        button.setOnAction(arg0 -> {
            stage.close();
            RootDisplayer gui = new RootDisplayer();
            try {
                gui.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        button.setId("create-editor-game");
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 15));
        button.setWrapText(true);
        button.setText("Launch Creator Studio");
        return button;
    }

    /**
     * creates the hover effects for the buttons
     *
     * @param button - button to add effects to
     */
    public void addListeners(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button.setStyle("-fx-background-color:" + theme.active.textColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.slideColour));

        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button.setStyle("-fx-background-color:" + theme.active.slideColour + "; -fx-border-width: 0px;" +
                        "-fx-text-fill: " + theme.active.textColour));
    }

    /**
     * Creates the button for the game player
     *
     * @param stage stage to display on
     * @return Button - Button that starts the game player
     */

    private Button createGPButton(Stage stage){
        Button button = createButton();
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 15));
        button.setWrapText(true);
        button.setText("Launch Player");

        button.setOnAction(arg0 -> {
            stage.close();
            GameRenderer gr = new GameRenderer();
            try {
                gr.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return button;
    }

    /**
     * Creates the button that exits the screen
     *
     * @param stage stage to close on click
     * @return Button that exits the screen
     */

    private Button createQuitButton(Stage stage){
        Button button = createButton();
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 15));
        button.setWrapText(true);
        button.setOnAction(arg0 -> stage.close());
        button.setText("Quit");
        return button;
    }

    /**
     * Creates the basic template for a button.
     *
     * @return - The button that was created
     */
    private Button createButton() {
        Button button = new Button();
        button.setFont(Font.font("Abyssinica SIL", FontWeight.BOLD, FontPosture.REGULAR, 25));
        button.setMaxWidth(MAX_VALUE);
        button.setPrefSize(180, 80);
        button.setCursor(Cursor.HAND);
        button.setWrapText(true);
        button.setStyle("-fx-background-color:" + theme.active.slideColour + ";" +" -fx-border-width: 2px;" + "-fx-text-fill: " + theme.active.textColour);
        addListeners(button);
        return button;
    }

    /**
     * Adds the fading in animation to the title screen.
     */
    private void addFadeAnimation(List<Node> nodes, Duration duration){
        for (Node node : nodes) {
            FadeTransition ft = new FadeTransition(duration, node);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }

    /**
     * Adds the moving animation to the title screen.
     */
    private void addMoveAnimation(Node title, Scene scene, Duration duration){
        double sceneWidth = scene.getHeight();
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(title);
        translate.setDuration(duration);
        translate.setByY(sceneWidth / 2.8);
        translate.play();
    }

}

