package client.DisplayGame;


import client.MainTitleScreen;
import client.PlayDisplayer;
import client.ThemeColours;
import entities.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The class that is in charge of rendering the game.
 */
public class GameRenderer extends Application implements PlayDisplayer {
    BorderPane root; // Creates JFrame that the GameRenderer will use to display the window
    ThemeColours theme;
    protected static Player player;
    protected static Stage stage;


    int animationSpeed;

    /**
     * The simple constructor for the GameRenderer that creates a JFrame to display the game
     */
    public GameRenderer() {
        root = new BorderPane();
        animationSpeed = 30;
        theme = new ThemeColours();
    }

    /**
     * Calls the launch function of JavaFX
     * @param args String needed for launch
     */
    public void begin(String[] args) {
        launch(args);
    }

    /**
     * Begins the display of the game
     *
     * @param primaryStage -  the stage that the game will be on
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Game Player");
        stage.setOnCloseRequest(we -> {
            MainTitleScreen title = new MainTitleScreen();
            title.start(new Stage());
        });
        TitleScreen titleScreen = new TitleScreen(theme, this);
        titleScreen.displayFirstSlide();
    }

    /**
     * This method displays the current slide on the screen
     */
    public void display() {
        CreateMenu menu = new CreateMenu(theme, this);
        MenuBar mbar = menu.createMenu();

        root = new BorderPane();
        root.setTop(mbar);

        CreateTextPane TPane = new CreateTextPane(player, theme, animationSpeed);
        root.setCenter(TPane.createTPane());
        root.setStyle("-fx-border-width: 5px; -fx-border-color: " + "#FFFFFF");
        CreateButtonPane buttonPane = new CreateButtonPane(player, theme);
        root.setBottom(buttonPane.createBPane());
        Scene scene = new Scene(root, 1200, 800);
        // Gets a custom font from google fonts
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the stage for the game
     */
    public void setStage(Stage stage) {
        GameRenderer.stage = stage;
    }

    /**
     * Sets the game player
     *
     * @param player The player to be used as the game player
     */
    public void setPlayer(Player player) {
        GameRenderer.player = player;
    }
}