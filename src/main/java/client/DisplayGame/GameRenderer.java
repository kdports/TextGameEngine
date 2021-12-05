package client.DisplayGame;


import client.MainTitleScreen;
import client.ThemeColours;
import entities.Player;
import entities.CreateSampleGame;
import client.PlayDisplayer;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static javafx.application.Application.launch;

/**
 * The class that is in charge of rendering the game.
 */
public class GameRenderer extends Application implements PlayDisplayer {
    BorderPane root; // Creates JFrame that the GameRenderer will use to display the window
    ThemeColours theme;
    protected static Player player;
    protected static Stage stage;

//    protected boolean closed = true;
    int animationSpeed;

    /**
     * The simple constructor for the GameRenderer that creates a JFrame to display the game
     */
    public GameRenderer() {
        root = new BorderPane();
        animationSpeed = 30;

        theme = new ThemeColours();
    }

    public void begin(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage)  {
        stage = primaryStage;
        stage.setTitle("First JavaFX Application");
        // Sets the image in the top left, currently no image
        // Image image = new Image("");
        // stage.getIcons().add(image);
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.displayFirstSlide();

    }

    /**
     * This method displays each of the slides onto the JFrame.
     */
    public void display() {
        CreateMenu menu = new CreateMenu(theme, player, animationSpeed, root, this);
        MenuBar mbar = menu.createMenu();

        root = new BorderPane();
        root.setTop(mbar);

        CreateTextPane TPane =  new CreateTextPane(player, theme, animationSpeed);
        root.setCenter(TPane.createTPane());
        root.setStyle("-fx-border-width: 5px; -fx-border-color: "+ "#FFFFFF");
        CreateButtonPane buttonPane = new CreateButtonPane(player, theme);
        root.setBottom(buttonPane.createBPane());
        Scene scene=new Scene(root,1200,800);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap");
        stage.setScene(scene);
//        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        stage.show();
    }

//    private <T extends Event> void closeWindowEvent(T t) {
//        if (closed){
//            GameRenderer gr = new GameRenderer();
//            try {
//                gr.start(new Stage());
//                closed = false;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void setStage(Stage stage){
        GameRenderer.stage = stage;
    }

    /**
     * Sets the game player
     * @param player The player to be used as the game player
     */
    public void setPlayer(Player player){
        GameRenderer.player = player;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        GameRenderer gr = new GameRenderer();
        // new Player(gr, CreateSampleGame.returnGame());
        gr.begin(args);
    }

}