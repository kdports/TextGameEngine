package client.DisplayGame;


import client.Theme;
import entities.Player;
import entities.CreateSampleGame;
import interfaces.PlayDisplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * The class that is in charge of rendering the game.
 */
public class GameRenderer extends Application implements PlayDisplayer {
    BorderPane root; // Creates JFrame that the GameRenderer will use to display the window
    Theme theme;
    private static Player player;
    private static Stage stage;


    int animationSpeed;

    /**
     * Creates the GameRenderer.
     */
    public GameRenderer() {
        root = new BorderPane();
        animationSpeed = 30;

        theme = new Theme();
    } //Initializes JFrame

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

        player.playGame();
    }

    /**
     * This method displays each of the slides onto the JFrame.
     */
    public void display() {
        root = new BorderPane();

        /* creates a scrolling display that contains the text and buttons for the game */
//        CreateMenu menu = new CreateMenu(theme, player, animationSpeed, frame, this);
//        frame.setJMenuBar(menu.createMenu());
//        CreateTextPanel textPanel = new CreateTextPanel(theme, player, animationSpeed);
//        textPanel.createTPanel();
//        JScrollPane textScroll = new JScrollPane(textPanel);
//        CreateButtonPanel panel = new CreateButtonPanel(player, theme);
//        panel.createBPanel();
//        JScrollPane buttonScroll = new JScrollPane(panel);
//        frame.add(textScroll);
//        frame.add(buttonScroll, BorderLayout.SOUTH);
//
//        frame.setVisible(true);

        // TODO: add the menu part
        CreateTextPane TPane =  new CreateTextPane(player, theme, animationSpeed);
        root.setCenter(TPane.createTPane());
        root.setStyle("-fx-border-width: 5px; -fx-border-color: #FFFFFF");
        CreateButtonPane buttonPane = new CreateButtonPane(player, theme);
        root.setBottom(buttonPane.createBPane());
        Scene scene=new Scene(root,1200,800);
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap");
        stage.setScene(scene);
        stage.show();
    }

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
        GameRenderer gr = new  GameRenderer();
        new Player(gr, CreateSampleGame.returnGame());
        gr.begin(args);
    }

}